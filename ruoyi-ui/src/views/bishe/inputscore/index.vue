<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="课号" prop="id">
        <el-input
          v-model="queryParams.id"
          placeholder="请输入课号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="学号" prop="studentNumber">
        <el-input
          v-model="queryParams.studentNumber"
          placeholder="请输入学号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="学生名字" prop="studentName">
        <el-input
          v-model="queryParams.studentName"
          placeholder="请输入学生名字"
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
          v-hasPermi="['bishe:inputscore:add']"
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['bishe:inputscore:edit']"
        >修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['bishe:inputscore:remove']"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['bishe:inputscore:export']"
        >导出
        </el-button>
      </el-col>

      <el-col :span="1.5">
        <el-button
          type="info"
          icon="el-icon-upload2"
          size="mini"
          @click="handleImport"
          v-hasPermi="['bishe:inputscore:import']"
        >导入
        </el-button>
      </el-col>



      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="inputscoreList" @selection-change="handleSelectionChange"
              height="600"
              border
              style="width: 100%">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="课号" align="center" prop="id" width="75"/>
      <el-table-column label="学号" align="center" prop="studentNumber"  width="120"/>
      <el-table-column label="学生名字" align="center" prop="studentName"/>
      <el-table-column label="第一大题成绩" align="center" prop="firQue"/>
      <el-table-column label="第二大题成绩" align="center" prop="secQue"/>
      <el-table-column label="第三大题成绩" align="center" prop="thiQue"/>
      <el-table-column label="第四大题成绩" align="center" prop="fouQue"/>
      <el-table-column label="第五大题成绩" align="center" prop="fifQue"/>
      <el-table-column label="第六大题成绩" align="center" prop="sixQue"/>
      <el-table-column label="第七答题成绩" align="center" prop="sevQue"/>
      <el-table-column label="第八答题成绩" align="center" prop="eigQue"/>
      <el-table-column label="第九答题成绩" align="center" prop="ninQue"/>
      <el-table-column label="第十大题成绩" align="center" prop="tenQue"/>
      <el-table-column label="期中成绩" align="center" prop="midtermSco"/>
      <el-table-column label="作业成绩" align="center" prop="homeworkSco"/>
      <el-table-column label="课堂测验" align="center" prop="quizSco"/>
      <el-table-column label="大作业" align="center" prop="largeSco"/>
      <el-table-column label="实验" align="center" prop="experimentSco"/>
      <el-table-column label="验收/答辩" align="center" prop="replySco"/>
      <el-table-column label="课程报告/论文" align="center" prop="thesisSco"/>
      <el-table-column label="考试成绩" align="center" prop="finalSco"/>
      <el-table-column label="总评成绩" align="center" prop="totalSco"/>
      <el-table-column label="成绩类别" align="center" prop="type"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['bishe:inputscore:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['bishe:inputscore:remove']"
          >删除
          </el-button>
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

    <!-- 添加或修改输入学生成绩对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-form-item label="课号" prop="id">
          <el-input v-model="form.id" placeholder="请输入课号"/>
        </el-form-item>
        <el-form-item label="学号" prop="studentNumber">
          <el-input v-model="form.studentNumber" placeholder="请输入学号"/>
        </el-form-item>
        <el-form-item label="学生名字" prop="studentName">
          <el-input v-model="form.studentName" placeholder="请输入学生名字"/>
        </el-form-item>
        <el-form-item label="第一大题成绩" prop="firQue">
          <el-input v-model="form.firQue" placeholder="请输入第一大题成绩"/>
        </el-form-item>
        <el-form-item label="第二大题成绩" prop="secQue">
          <el-input v-model="form.secQue" placeholder="请输入第二大题成绩"/>
        </el-form-item>
        <el-form-item label="第三大题成绩" prop="thiQue">
          <el-input v-model="form.thiQue" placeholder="请输入第三大题成绩"/>
        </el-form-item>
        <el-form-item label="第四大题成绩" prop="fouQue">
          <el-input v-model="form.fouQue" placeholder="请输入第四大题成绩"/>
        </el-form-item>
        <el-form-item label="第五大题成绩" prop="fifQue">
          <el-input v-model="form.fifQue" placeholder="请输入第五大题成绩"/>
        </el-form-item>
        <el-form-item label="第六大题成绩" prop="sixQue">
          <el-input v-model="form.sixQue" placeholder="请输入第六大题成绩"/>
        </el-form-item>
        <el-form-item label="第七答题成绩" prop="sevQue">
          <el-input v-model="form.sevQue" placeholder="请输入第七答题成绩"/>
        </el-form-item>
        <el-form-item label="第八答题成绩" prop="eigQue">
          <el-input v-model="form.eigQue" placeholder="请输入第八答题成绩"/>
        </el-form-item>
        <el-form-item label="第九答题成绩" prop="ninQue">
          <el-input v-model="form.ninQue" placeholder="请输入第九答题成绩"/>
        </el-form-item>
        <el-form-item label="第十大题成绩" prop="tenQue">
          <el-input v-model="form.tenQue" placeholder="请输入第十大题成绩"/>
        </el-form-item>
        <el-form-item label="期中成绩" prop="midtermSco">
          <el-input v-model="form.midtermSco" placeholder="请输入期中成绩"/>
        </el-form-item>
        <el-form-item label="作业成绩" prop="homeworkSco">
          <el-input v-model="form.homeworkSco" placeholder="请输入作业成绩"/>
        </el-form-item>
        <el-form-item label="课堂测验" prop="quizSco">
          <el-input v-model="form.quizSco" placeholder="请输入课堂测验"/>
        </el-form-item>
        <el-form-item label="大作业" prop="largeSco">
          <el-input v-model="form.largeSco" placeholder="请输入大作业"/>
        </el-form-item>
        <el-form-item label="实验" prop="experimentSco">
          <el-input v-model="form.experimentSco" placeholder="请输入实验"/>
        </el-form-item>
        <el-form-item label="验收/答辩" prop="replySco">
          <el-input v-model="form.replySco" placeholder="请输入验收/答辩"/>
        </el-form-item>
        <el-form-item label="课程报告/论文" prop="thesisSco">
          <el-input v-model="form.thesisSco" placeholder="请输入课程报告/论文"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 批量导入成绩对话框 -->
    <el-dialog :title="upload.title" :visible.sync="upload.open" width="400px">
      <el-upload
        ref="upload"
        :limit="1"
        accept=".xlsx, .xls"
        :headers="upload.headers"
        :action="upload.url + '?updateSupport=' + upload.updateSupport"
        :disabled="upload.isUploading"
        :on-progress="handleFileUploadProgress"
        :on-success="handleFileSuccess"
        :auto-upload="false"
        drag
      >
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">
          将文件拖到此处，或
          <em>点击上传</em>
        </div>
<!--        <div class="el-upload__tip" slot="tip">
          <el-checkbox v-model="upload.updateSupport"/>
          是否更新已经存在的用户数据
        </div>-->
        <div class="el-upload__tip" style="color:red" slot="tip">提示：仅允许导入“xls”或“xlsx”格式文件！</div>
      </el-upload>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitFileForm">确 定</el-button>
        <el-button @click="upload.open = false">取 消</el-button>
      </div>
    </el-dialog>

  </div>
</template>


<script>
import {listInputscore, getInputscore, delInputscore, addInputscore, updateInputscore} from "@/api/bishe/inputscore";
import {getToken} from "@/utils/auth";
import {importTemplate} from "@/api/system/user";

export default {
  name: "Inputscore",
  data() {
    return {
      flag: null,
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      nums: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 输入学生成绩表格数据
      inputscoreList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        id: null,
        studentNumber: null,
        studentName: null,
        firQue: null,
        secQue: null,
        thiQue: null,
        fouQue: null,
        fifQue: null,
        sixQue: null,
        sevQue: null,
        eigQue: null,
        ninQue: null,
        tenQue: null,
        midtermSco: null,
        homeworkSco: null,
        quizSco: null,
        largeSco: null,
        experimentSco: null,
        replySco: null,
        thesisSco: null,
        finalSco: null,
        totalSco: null,
        type: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        id: [
          {required: true, message: "课号不能为空", trigger: "blur"}
        ],
        studentNumber: [
          {required: true, message: "学号不能为空", trigger: "blur"}
        ],
        studentName: [
          {required: true, message: "学生名字不能为空", trigger: "blur"}
        ],
      },
      // 用户导入参数
      upload: {
        // 是否显示弹出层（用户导入）
        open: false,
        // 弹出层标题（用户导入）
        title: "从excel中批量导入成绩",
        // 是否禁用上传
        isUploading: false,
        // 是否更新已经存在的用户数据
        updateSupport: 0,
        // 设置上传的请求头部
        headers: {Authorization: "Bearer " + getToken()},
        // 上传的地址
        url: process.env.VUE_APP_BASE_API + "/bishe/inputscore/importData"
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询输入学生成绩列表 */
    getList() {
      this.loading = true;
      listInputscore(this.queryParams).then(response => {
        this.inputscoreList = response.rows;
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
        flag: null,
        id: null,
        studentNumber: null,
        studentName: null,
        firQue: null,
        secQue: null,
        thiQue: null,
        fouQue: null,
        fifQue: null,
        sixQue: null,
        sevQue: null,
        eigQue: null,
        ninQue: null,
        tenQue: null,
        midtermSco: null,
        homeworkSco: null,
        quizSco: null,
        largeSco: null,
        experimentSco: null,
        replySco: null,
        thesisSco: null,
        finalSco: null,
        totalSco: null,
        type: null
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
      this.ids = selection.map(item => item.id)
      this.nums = selection.mmap(item => item.studentNumber)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.flag = 0;
      this.reset();
      this.open = true;
      this.title = "添加输入学生成绩";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.flag = 1;
      this.reset();
      const id = row.id || this.ids
      const num = row.studentNumber || this.nums
      getInputscore(id, num).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改输入学生成绩";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          /*this.form.id != null */
          if (this.flag) {
            updateInputscore(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addInputscore(this.form).then(response => {
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
      const ids = row.id || this.ids;
      const num = row.studentNumber;
        this.$modal.confirm('是否确认删除输入学生成绩编号为"' + ids + '"的数据项？').then(function () {
        return delInputscore(ids,num);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('bishe/inputscore/export', {
        ...this.queryParams
      }, `inputscore_${new Date().getTime()}.xlsx`)
    },


    /** 导入按钮操作 */
    handleImport() {
      this.upload.title = "用户导入";
      this.upload.open = true;
    },

// 文件上传中处理
    handleFileUploadProgress(event, file, fileList) {
      this.upload.isUploading = true;
    },
// 文件上传成功处理
    handleFileSuccess(response, file, fileList) {
      this.upload.open = false;
      this.upload.isUploading = false;
      this.$refs.upload.clearFiles();
      this.$alert(response.msg, "导入结果", {dangerouslyUseHTMLString: true});
      this.getList();
    },
// 提交上传文件
    submitFileForm() {
      this.$refs.upload.submit();
    },

  }
};


</script>
