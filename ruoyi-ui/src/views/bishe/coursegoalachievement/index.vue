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
<!--      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['bishe:coursegoalachievement:add']"
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
          v-hasPermi="['bishe:coursegoalachievement:edit']"
        >修改</el-button>
      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['bishe:coursegoalachievement:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['bishe:coursegoalachievement:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="coursegoalachievementList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="课号" align="center" prop="courseId" />
      <el-table-column label="课程目标" align="center" prop="courseObjectives" />
      <el-table-column label="第一大题比例" align="center" prop="firRatio" />
      <el-table-column label="第二大题比例" align="center" prop="secRatio" />
      <el-table-column label="第三大题比例" align="center" prop="thiRatio" />
      <el-table-column label="第四大题比例" align="center" prop="fouRatio" />
      <el-table-column label="第五大题比例" align="center" prop="fifRatio" />
      <el-table-column label="第六大题比例" align="center" prop="sixRatio" />
      <el-table-column label="第七大题比例" align="center" prop="sevRatio" />
      <el-table-column label="第八大题比例" align="center" prop="eigRatio" />
      <el-table-column label="第九大题比例" align="center" prop="ninRatio" />
      <el-table-column label="第十大题比例" align="center" prop="tenRatio" />
      <el-table-column label="期中成绩比例" align="center" prop="midtermRatio" />
      <el-table-column label="作业成绩比例" align="center" prop="homeworkRatio" />
      <el-table-column label="课堂测验比例" align="center" prop="quizRatio" />
      <el-table-column label="大作业比例" align="center" prop="largeRatio" />
      <el-table-column label="实验比例" align="center" prop="experimentRatio" />
      <el-table-column label="验收/答辩比例" align="center" prop="replyRatio" />
      <el-table-column label="课程报告比例/论文" align="center" prop="thesisRatio" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
<!--          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['bishe:coursegoalachievement:edit']"
          >修改</el-button>-->
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['bishe:coursegoalachievement:remove']"
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

    <!-- 添加或修改课程目标达成度表对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listCoursegoalachievement, getCoursegoalachievement, delCoursegoalachievement, addCoursegoalachievement, updateCoursegoalachievement } from "@/api/bishe/coursegoalachievement";

export default {
  name: "Coursegoalachievement",
  data() {
    return {
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
      // 课程目标达成度表表格数据
      coursegoalachievementList: [],
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
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询课程目标达成度表列表 */
    getList() {
      this.loading = true;
      listCoursegoalachievement(this.queryParams).then(response => {
        this.coursegoalachievementList = response.rows;
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
        courseId: null,
        courseObjectives: null,
        firRatio: null,
        secRatio: null,
        thiRatio: null,
        fouRatio: null,
        fifRatio: null,
        sixRatio: null,
        sevRatio: null,
        eigRatio: null,
        ninRatio: null,
        tenRatio: null,
        midtermRatio: null,
        homeworkRatio: null,
        quizRatio: null,
        largeRatio: null,
        experimentRatio: null,
        replyRatio: null,
        thesisRatio: null
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
      this.reset();
      this.open = true;
      this.title = "添加课程目标达成度表";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const courseId = row.courseId || this.ids

      getCoursegoalachievement(courseId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改课程目标达成度表";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.courseId != null) {
            updateCoursegoalachievement(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCoursegoalachievement(this.form).then(response => {
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
        return delCoursegoalachievement(courseIds,courseObjectives);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('bishe/coursegoalachievement/export', {
        ...this.queryParams
      }, `coursegoalachievement_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
