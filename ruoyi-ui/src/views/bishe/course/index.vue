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
      <el-form-item label="课程名字" prop="courseName">
        <el-input
          v-model="queryParams.courseName"
          placeholder="请输入课程名字"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="教师名字" prop="teacherName">
        <el-input
          v-model="queryParams.teacherName"
          placeholder="请输入教师名字"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
     <el-form-item label="专业" prop="major">
        <el-select
          v-model="queryParams.major"
          placeholder="请输入专业"
          clearable
        >
          <el-option
            v-for="item in major"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          ></el-option>
        </el-select>
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
          v-hasPermi="['bishe:course:add']"
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
          v-hasPermi="['bishe:course:edit']"
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
          v-hasPermi="['bishe:course:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['bishe:course:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="courseList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="课号" align="center" prop="courseId" />
      <el-table-column label="课程名字" align="center" prop="courseName" />
      <el-table-column label="学年" align="center" prop="term" />
      <el-table-column label="学期" align="center" prop="xueqi" />
      <el-table-column label="教师名字" align="center" prop="teacherName" />
      <el-table-column label="专业" align="center" prop="major"/>
      <el-table-column label="学时" align="center" prop="classHour" />
      <el-table-column label="学分" align="center" prop="credit" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="zhiliang(scope.row)"
            v-hasPermi="['bishe:course:zhiliang']"
          >质量报告</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['bishe:course:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['bishe:course:remove']"
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

    <!-- 添加或修改总课程表对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="课号" prop="courseId">
          <el-input v-model="form.courseId" placeholder="请输入课号" />
        </el-form-item>
        <el-form-item label="课程名字" prop="courseName">
          <el-input v-model="form.courseName" placeholder="请输入课程名字" />
        </el-form-item>
        <el-form-item label="学年" prop="term">
          <el-input v-model="form.term" placeholder="请输入学年" />
        </el-form-item>
        <el-form-item label="学期" prop="term">
          <el-input v-model="form.xueqi" placeholder="请输入学期:1或2" />
        </el-form-item>
        <el-form-item label="教师名字" prop="teacherName">
          <el-input v-model="form.teacherName" placeholder="请输入教师名字" />
        </el-form-item>
        <el-form-item label="专业" prop="major">
          <el-select v-model="form.major" placeholder="请选择专业">
            <el-option
              v-for="item in major"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="学时" prop="classHour">
          <el-input v-model="form.classHour" placeholder="请输入学时" />
        </el-form-item>
        <el-form-item label="学分" prop="credit">
          <el-input v-model="form.credit" placeholder="请输入学分" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 生成质量分析报告对话框 -->
    <el-dialog :title="title" :visible.sync="openzhiliang" width="500px" append-to-body>
      <el-form ref="zhiliang" :model="zhiliangform" :rules="rules1" label-width="120px">
        <el-form-item label="课程代码" prop="courseCode">
          <el-input v-model="zhiliangform.courseCode" placeholder="请输入课程代码" />
        </el-form-item>
        <el-form-item label="考试日期" prop="date">
          <el-input v-model="zhiliangform.date" placeholder="请输入考试日期" />
        </el-form-item>
        <el-form-item label="应考人数：" prop="totalNum">
          <el-input v-model="zhiliangform.totalNum" placeholder="请输入应考人数" />
        </el-form-item>
        <el-form-item label="实考人数：" prop="realityNum">
          <el-input v-model="zhiliangform.realityNum" placeholder="请输入实考人数" />
        </el-form-item>
        <el-form-item label="缓考人数：" prop="delayNum">
          <el-input v-model="zhiliangform.delayNum" placeholder="请输入缓考人数" />
        </el-form-item>
        <el-form-item label="旷考人数：" prop="missNum">
          <el-input v-model="zhiliangform.missNum" placeholder="请输入旷考人数" />
        </el-form-item>
        <el-form-item label="取消考试资格人数：" prop="cancelNum">
          <el-input v-model="zhiliangform.cancelNum" placeholder="请输入取消考试资格人数" />
        </el-form-item>
        <el-form-item label="期中成绩比例：" prop="midtermSco">
          <el-input v-model="zhiliangform.midtermSco" placeholder="请输入期中成绩比例" />
        </el-form-item>
        <el-form-item label="作业成绩比例：" prop="homeworkSco">
          <el-input v-model="zhiliangform.homeworkSco" placeholder="请输入作业成绩比例" />
        </el-form-item>
        <el-form-item label="课堂测验比例：" prop="quizSco">
          <el-input v-model="zhiliangform.quizSco" placeholder="请输入课堂测验比例" />
        </el-form-item>
        <el-form-item label="大作业比例：" prop="largeSco">
          <el-input v-model="zhiliangform.largeSco" placeholder="请输入大作业比例" />
        </el-form-item>
        <el-form-item label="实验比例：" prop="experimentSco">
          <el-input v-model="zhiliangform.experimentSco" placeholder="请输入实验比例" />
        </el-form-item>
        <el-form-item label="验收/答辩比例：" prop="replySco">
          <el-input v-model="zhiliangform.replySco" placeholder="请输入验收/答辩比例" />
        </el-form-item>
        <el-form-item label="课程报告/论文比例：" prop="thesisSco">
          <el-input v-model="zhiliangform.thesisSco" placeholder="请输入课程报告/论文比例" />
        </el-form-item>
        <el-form-item label="期末考试比例：" prop="finalSco">
          <el-input v-model="zhiliangform.finalSco" placeholder="请输入期末考试比例" />
        </el-form-item>
        <el-form-item label="其他比例：" prop="other">
          <el-input v-model="zhiliangform.other" placeholder="请输入其他比例" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitZhiliangForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import { listCourse, getCourse, delCourse, addCourse, updateCourse,getZhiliang } from "@/api/bishe/course";

export default {
  name: "Course",
  data() {
    return {
      flag:null,
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
      // 总课程表表格数据
      courseList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      openzhiliang: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        courseId: null,
        courseName: null,
        teacherName: null,
        major: null,
      },
      // 表单参数
      form: {},
      //质量报告表单参数
      zhiliangform:{},
      // 表单校验
      rules: {
        courseId: [
          { required: true, message: "课号不能为空", trigger: "blur" }
        ],
        courseName: [
          { required: true, message: "课程名字不能为空", trigger: "blur" }
        ],
        teacherName: [
          { required: true, message: "教师名字不能为空", trigger: "blur" }
        ],
        major: [
          { required: true, message: "课程专业不能为空", trigger: "blur" }
        ],
      },
      rules1:{
        courseCode:[{
          required: true, message: "课程代码不能为空", trigger: "blur"
        }],
        date:[{
          required: true, message: "考试日期不能为空", trigger: "blur"
        }],
        totalNum:[{
          required: true, message: "应考人数不能为空", trigger: "blur"
        }],
        realityNum:[{
          required: true, message: "实考人数不能为空", trigger: "blur"
        }],
        delayNum:[{
          required: true, message: "缓考人数不能为空", trigger: "blur"
        }],
        missNum:[{
          required: true, message: "旷考人数不能为空", trigger: "blur"
        }],
        cancelNum:[{
          required: true, message: "取消考试资格人数不能为空", trigger: "blur"
        }]
      },
      major: [{
        value: '信息对抗技术',
        label: '信息对抗技术'
      }, {
        value: '计算机科学与技术',
        label: '计算机科学与技术'
      }, {
        value: '软件工程',
        label: '软件工程'
      }, {
        value: '物联网工程',
        label: '物联网工程'
      }, {
        value: '信息安全',
        label: '信息安全'
      }, {
        value: '智能科学与技术',
        label: '智能科学与技术'
      }],
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询总课程表列表 */
    getList() {
      this.loading = true;
      listCourse(this.queryParams).then(response => {
        this.courseList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.openzhiliang=false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        flag:null,
        courseId: null,
        courseName: null,
        term: null,
        xueqi:null,
        teacherName: null,
        major: null,
        classHour: null,
        credit: null
      };
      this.resetForm("form");
      this.zhiliangform={
        courseId:null,
        courseCode:null,
        date:null,
        totalNum:null,
        realityNum:null,
        delayNum:null,
        missNum:null,
        cancelNum:null,
      };
      this.resetForm("zhiliangform");
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
      this.flag=0;
      this.reset();
      this.open = true;
      this.title = "添加总课程表";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.flag=1;
      this.reset();
      const courseId = row.courseId || this.ids
      getCourse(courseId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改总课程表";
      });
    },

    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          /*this.form.courseId != null*/
          if (this.flag) {
            updateCourse(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCourse(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 提交质量报告按钮 */
    submitZhiliangForm() {
      console.log(this.zhiliangform);
       this.$refs["zhiliang"].validate(valid => {
         if (valid) {
           this.download('bishe/course/zhiliang', {
             ...this.zhiliangform
           }, `课程质量报告_${new Date().getTime()}.docx`)
         }
         this.openzhiliang=false;
       });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const courseIds = row.courseId || this.ids;
      this.$modal.confirm('是否确认删除总课程表编号为"' + courseIds + '"的数据项？').then(function() {
        return delCourse(courseIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('bishe/course/export', {
        ...this.queryParams
      }, `质量报告_${new Date().getTime()}.xlsx`)
    },
    /** 生成质量报告按钮操作 */
    zhiliang(row) {
      this.reset();
      this.zhiliangform.courseId=row.courseId;
      this.openzhiliang = true;
      this.title = "生成质量报告";
       // this.download('bishe/course/zhiliang', {
       //   courseId:row.courseId
       // }, `课程质量报告_${new Date().getTime()}.docx`)
    },
    /** 修改按钮操作 */
    gaijin(row) {
      const courseId = row.courseId
    },
  }
};
</script>
