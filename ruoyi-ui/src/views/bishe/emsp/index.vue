<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="课号" prop="courseId">
        <el-input
          v-model="queryParams.courseId"
          placeholder="请输入课号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="课程目标" prop="courseObjectives">
        <el-input
          v-model="queryParams.courseObjectives"
          placeholder="请输入课程目标"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['bishe:emsp:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['bishe:emsp:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['bishe:emsp:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['bishe:emsp:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="emspList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="课号" align="center" prop="courseId" />
      <el-table-column label="课程目标" align="center" prop="courseObjectives" />
      <el-table-column label="支撑毕业要求指标点" align="center" prop="requirementPoints" />
      <el-table-column label="期中成绩" align="center" prop="midtermSco" />
      <el-table-column label="作业成绩" align="center" prop="homeworkSco" />
      <el-table-column label="课堂测验" align="center" prop="quizSco" />
      <el-table-column label="大作业" align="center" prop="largeSco" />
      <el-table-column label="实验" align="center" prop="experimentSco" />
      <el-table-column label="验收/答辩" align="center" prop="replySco" />
      <el-table-column label="课程报告/论文" align="center" prop="thesisSco" />
      <el-table-column label="期末考试" align="center" prop="finalSco" />
      <el-table-column label="成绩比例" align="center" prop="ratio" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['bishe:emsp:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['bishe:emsp:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改评价方式及成绩比例对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="课号" prop="courseId">
          <el-input v-model="form.courseId" placeholder="请输入课号" />
        </el-form-item>
        <el-form-item label="课程目标" prop="courseObjectives">
          <el-input v-model="form.courseObjectives" placeholder="请输入课程目标" />
        </el-form-item>
        <el-form-item label="支撑毕业要求指标点" prop="requirementPoints">
          <el-input v-model="form.requirementPoints" placeholder="请输入支撑毕业要求指标点" />
        </el-form-item>
        <el-form-item label="期中成绩" prop="midtermSco">
          <el-input v-model="form.midtermSco" placeholder="请输入期中成绩成绩比例" />
        </el-form-item>
        <el-form-item label="作业成绩" prop="homeworkSco">
          <el-input v-model="form.homeworkSco" placeholder="请输入作业成绩成绩比例" />
        </el-form-item>
        <el-form-item label="课堂测验" prop="quizSco">
          <el-input v-model="form.quizSco" placeholder="请输入课堂测验成绩比例" />
        </el-form-item>
        <el-form-item label="大作业" prop="largeSco">
          <el-input v-model="form.largeSco" placeholder="请输入大作业成绩比例" />
        </el-form-item>
        <el-form-item label="实验" prop="experimentSco">
          <el-input v-model="form.experimentSco" placeholder="请输入实验成绩比例" />
        </el-form-item>
        <el-form-item label="验收/答辩" prop="replySco">
          <el-input v-model="form.replySco" placeholder="请输入验收/答辩成绩比例" />
        </el-form-item>
        <el-form-item label="课程报告/论文" prop="thesisSco">
          <el-input v-model="form.thesisSco" placeholder="请输入课程报告/论文成绩比例" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listEmsp, getEmsp, delEmsp, addEmsp, updateEmsp } from "@/api/bishe/emsp";

export default {
  name: "Emsp",
  data() {
    return {
      flag:null,
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      //选中的课程目标数组
      objectives:[],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 评价方式及成绩比例表格数据
      emspList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        courseId: null,
        courseObjectives: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        courseId: [
          { required: true, message: "课号不能为空", trigger: "blur" }
        ],
        courseObjectives: [
          { required: true, message: "课程目标不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询评价方式及成绩比例列表 */
    getList() {
      this.loading = true;
      listEmsp(this.queryParams).then(response => {
        this.emspList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        flag:null,
        courseId: null,
        courseObjectives: null,
        requirementPoints: null,
        midtermSco: null,
        homeworkSco: null,
        quizSco: null,
        largeSco: null,
        experimentSco: null,
        replySco: null,
        thesisSco: null,
        finalSco: null,
        ratio: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.courseId)
      this.objectives  = selection.map(item => item.courseObjectives)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.flag=0;
      this.reset();
      this.open = true;
      this.title = "添加评价方式及成绩比例";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.flag=1;
      this.reset();
      const courseId = row.courseId || this.ids
      const courseObjectives = row.courseObjectives || this.objectives;
      getEmsp(courseId,courseObjectives).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改评价方式及成绩比例";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.flag) {
            updateEmsp(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addEmsp(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const courseIds = row.courseId || this.ids;
      const courseObjectives = row.courseObjectives || this.objectives;
      this.$modal.confirm('是否确认删除编号为"' + courseIds + '"和"'+courseObjectives+'"的数据项？').then(function() {
        return delEmsp(courseIds,courseObjectives);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('bishe/emsp/export', {
        ...this.queryParams
      }, `emsp_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
