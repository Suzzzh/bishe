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
          v-hasPermi="['bishe:finalexamscoredistribution:add']"
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
          v-hasPermi="['bishe:finalexamscoredistribution:edit']"
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
          v-hasPermi="['bishe:finalexamscoredistribution:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['bishe:finalexamscoredistribution:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="finalexamscoredistributionList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="课号" align="center" prop="courseId" />
      <el-table-column label="课程目标" align="center" prop="courseObjectives" />
      <el-table-column label="卷面满分" align="center" prop="maxSco" />
      <el-table-column label="第一大题" align="center" prop="firQue" />
      <el-table-column label="第二大题" align="center" prop="secQue" />
      <el-table-column label="第三大题" align="center" prop="thiQue" />
      <el-table-column label="第四大题" align="center" prop="fouQue" />
      <el-table-column label="第五大题" align="center" prop="fifQue" />
      <el-table-column label="第六大题" align="center" prop="sixQue" />
      <el-table-column label="第七大题" align="center" prop="sevQue" />
      <el-table-column label="第八大题" align="center" prop="eigQue" />
      <el-table-column label="第九大题" align="center" prop="ninQue" />
      <el-table-column label="第十大题" align="center" prop="tenQue" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['bishe:finalexamscoredistribution:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['bishe:finalexamscoredistribution:remove']"
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

    <!-- 添加或修改期末考试各题支撑目标分数分配对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="课号" prop="courseId">
          <el-input v-model="form.courseId" placeholder="请输入课号" />
        </el-form-item>
        <el-form-item label="课程目标" prop="courseObjectives">
          <el-input v-model="form.courseObjectives" placeholder="请输入课程目标" />
        </el-form-item>
        <el-form-item label="第一大题" prop="firQue">
          <el-input v-model="form.firQue" placeholder="请输入第一大题" />
        </el-form-item>
        <el-form-item label="第二大题" prop="secQue">
          <el-input v-model="form.secQue" placeholder="请输入第二大题" />
        </el-form-item>
        <el-form-item label="第三大题" prop="thiQue">
          <el-input v-model="form.thiQue" placeholder="请输入第三大题" />
        </el-form-item>
        <el-form-item label="第四大题" prop="fouQue">
          <el-input v-model="form.fouQue" placeholder="请输入第四大题" />
        </el-form-item>
        <el-form-item label="第五大题" prop="fifQue">
          <el-input v-model="form.fifQue" placeholder="请输入第五大题" />
        </el-form-item>
        <el-form-item label="第六大题" prop="sixQue">
          <el-input v-model="form.sixQue" placeholder="请输入第六大题" />
        </el-form-item>
        <el-form-item label="第七大题" prop="sevQue">
          <el-input v-model="form.sevQue" placeholder="请输入第七大题" />
        </el-form-item>
        <el-form-item label="第八大题" prop="eigQue">
          <el-input v-model="form.eigQue" placeholder="请输入第八大题" />
        </el-form-item>
        <el-form-item label="第九大题" prop="ninQue">
          <el-input v-model="form.ninQue" placeholder="请输入第九大题" />
        </el-form-item>
        <el-form-item label="第十大题" prop="tenQue">
          <el-input v-model="form.tenQue" placeholder="请输入第十大题" />
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
import { listFinalexamscoredistribution, getFinalexamscoredistribution, delFinalexamscoredistribution, addFinalexamscoredistribution, updateFinalexamscoredistribution } from "@/api/bishe/finalexamscoredistribution";

export default {
  name: "Finalexamscoredistribution",
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
      // 期末考试各题支撑目标分数分配表格数据
      finalexamscoredistributionList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        courseId: null,
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
    /** 查询期末考试各题支撑目标分数分配列表 */
    getList() {
      this.loading = true;
      listFinalexamscoredistribution(this.queryParams).then(response => {
        this.finalexamscoredistributionList = response.rows;
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
        maxSco: null,
        firQue: null,
        secQue: null,
        thiQue: null,
        fouQue: null,
        fifQue: null,
        sixQue: null,
        sevQue: null,
        eigQue: null,
        ninQue: null,
        tenQue: null
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
      this.title = "添加期末考试各题支撑目标分数分配";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.flag=1;
      this.reset();
      const courseId = row.courseId || this.ids;
      const courseObjectives = row.courseObjectives || this.objectives;
      getFinalexamscoredistribution(courseId,courseObjectives).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改期末考试各题支撑目标分数分配";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.flag) {
            updateFinalexamscoredistribution(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addFinalexamscoredistribution(this.form).then(response => {
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
        return delFinalexamscoredistribution(courseIds,courseObjectives);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('bishe/finalexamscoredistribution/export', {
        ...this.queryParams
      }, `finalexamscoredistribution_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
