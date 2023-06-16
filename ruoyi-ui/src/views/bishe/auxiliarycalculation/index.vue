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
          v-hasPermi="['bishe:auxiliarycalculation:add']"
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
          v-hasPermi="['bishe:auxiliarycalculation:edit']"
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
          v-hasPermi="['bishe:auxiliarycalculation:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['bishe:auxiliarycalculation:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="auxiliarycalculationList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="课号" align="center" prop="courseId" />
      <el-table-column label="第一大题满分" align="center" prop="firMax" />
      <el-table-column label="第二大题满分" align="center" prop="secMax" />
      <el-table-column label="第三大题满分" align="center" prop="thiMax" />
      <el-table-column label="第四大题满分" align="center" prop="fouMax" />
      <el-table-column label="第五大题满分" align="center" prop="fifMax" />
      <el-table-column label="第六大题满分" align="center" prop="sixMax" />
      <el-table-column label="第七大题满分" align="center" prop="sevMax" />
      <el-table-column label="第八大题满分" align="center" prop="eigMax" />
      <el-table-column label="第九大题满分" align="center" prop="ninMax" />
      <el-table-column label="第十大题满分" align="center" prop="tenMax" />
      <el-table-column label="期中成绩满分" align="center" prop="midtermMax" />
      <el-table-column label="作业成绩满分" align="center" prop="homeworkMax" />
      <el-table-column label="课堂测验满分" align="center" prop="quizMax" />
      <el-table-column label="大作业满分" align="center" prop="largeMax" />
      <el-table-column label="实验满分" align="center" prop="experimentMax" />
      <el-table-column label="验收/答辩满分" align="center" prop="replyMax" />
      <el-table-column label="课程报告/论文满分" align="center" prop="thesisMax" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['bishe:auxiliarycalculation:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['bishe:auxiliarycalculation:remove']"
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

    <!-- 添加或修改辅助计算表对话框 -->
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
import { listAuxiliarycalculation, getAuxiliarycalculation, delAuxiliarycalculation, addAuxiliarycalculation, updateAuxiliarycalculation } from "@/api/bishe/auxiliarycalculation";

export default {
  name: "Auxiliarycalculation",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 辅助计算表表格数据
      auxiliarycalculationList: [],
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
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询辅助计算表列表 */
    getList() {
      this.loading = true;
      listAuxiliarycalculation(this.queryParams).then(response => {
        this.auxiliarycalculationList = response.rows;
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
        firMax: null,
        secMax: null,
        thiMax: null,
        fouMax: null,
        fifMax: null,
        sixMax: null,
        sevMax: null,
        eigMax: null,
        ninMax: null,
        tenMax: null,
        midtermMax: null,
        homeworkMax: null,
        quizMax: null,
        largeMax: null,
        experimentMax: null,
        replyMax: null,
        thesisMax: null
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
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加辅助计算表";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const courseId = row.courseId || this.ids
      getAuxiliarycalculation(courseId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改辅助计算表";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.courseId != null) {
            updateAuxiliarycalculation(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addAuxiliarycalculation(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除辅助计算表编号为"' + courseIds + '"的数据项？').then(function() {
        return delAuxiliarycalculation(courseIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('bishe/auxiliarycalculation/export', {
        ...this.queryParams
      }, `auxiliarycalculation_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
