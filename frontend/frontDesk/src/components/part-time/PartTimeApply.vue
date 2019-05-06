<template>
  <section class="part-time-apply-container">
    <el-dialog :title="partTimeDetail.name" :visible.sync="partTimeApplyContainerVisibleFlag">
      <section class="complete-apply-container">
        <div class="alert">完善报名信息</div>
        <el-form ref="partTimeApplyForm" :rules="partTimeApplyFormRules" :model="partTimeApplyForm" label-width="100px">
          <el-form-item label="性名" prop="username">
            <el-input v-model="partTimeApplyForm.username"></el-input>
          </el-form-item>
          <el-form-item label="电话" prop="phone">
            <el-input v-model="partTimeApplyForm.phone"></el-input>
          </el-form-item>
        </el-form>
      </section>
      <section class="confirm-working-container">
        <div class="alert">确定工作时间和地点</div>
        <el-form ref="form" label-width="100px">
          <el-form-item label="工作地址">
            <el-input :value="`${partTimeDetail.city} - ${partTimeDetail.area} - ${partTimeDetail.address}`" disabled></el-input>
          </el-form-item>
          <el-form-item label="工作日期">
            <el-input :value="`${partTimeDetail.workingDateStart} 至 ${partTimeDetail.workingDateEnd}`" disabled></el-input>
          </el-form-item>
          <el-form-item label="工作时间">
            <el-input :value="`${partTimeDetail.workingTimeWeek}`" disabled></el-input>
          </el-form-item>
          <el-form-item label="上班时间">
            <el-input :value="`${partTimeDetail.workingTime}`" disabled></el-input>
          </el-form-item>
        </el-form>
      </section>
      <div class="confirm-container">
        <el-button type="danger" @click="confirm">完成报名</el-button>
        <el-button @click="partTimeApplyContainerVisibleFlag = false">取 消</el-button>
      </div>
    </el-dialog>
  </section>
</template>

<script>
import NProgress from 'nprogress';
import api from '@/api';

export default {
  name: 'PartTimeApply',
  data() {
    return {
      partTimeApplyContainerVisibleFlag: false,
      partTimeApplyForm: {
        username: '',
        phone: ''
      },
      partTimeApplyFormRules: {
        username: [
          { required: true, message: '请输入性名', trigger: 'change' },
          { min: 2, max: 20, message: '性名由2~20位字符组成', trigger: 'change' }
        ],
        phone: [
          { required: true, message: '请输入手机号', trigger: 'change' },
          { pattern: /^1[3|4|5|7|8][0-9]\d{8}$/, message: '手机号不正确', trigger: 'change' }
        ]
      }
    };
  },
  props: {
    partTimeDetail: {
      type: Object,
      required: true
    }
  },
  watch: {
    // 监听 url
    $route: {
      handler() {
        // url 只要变动关闭弹框
        this.partTimeApplyContainerVisibleFlag = false;
      }
    }
  },
  methods: {
    confirm() {
      this.$refs['partTimeApplyForm'].validate(async valid => {
        if (valid) {
          NProgress.start();
          const result = await api.partTime.applyPartTime({
            partTimeId: this.$route.params.id,
            name: this.partTimeApplyForm.username,
            phone: this.partTimeApplyForm.phone
          });
          NProgress.done();
          if (result.code === 0) {
            this.$notify({
              message: '报名成功',
              position: 'bottom-left',
              duration: 3000,
              showClose: false
            });
            // 关闭区域选择框
            this.partTimeApplyContainerVisibleFlag = false;
            // 清空表单
            this.$refs['partTimeApplyForm'].resetFields();
          } else {
            this.$notify({
              message: result.message,
              position: 'bottom-left',
              duration: 3000,
              showClose: false
            });
          }
        }
      });
    }
  }
};
</script>

<style lang="scss">
  .part-time-apply-container {
    .el-dialog {
      width: 750px;
      margin-top: 100px !important;
      padding-bottom: 50px;
      .el-dialog__body {
        padding-left: 25px;
        padding-right: 25px;
        .alert {
          margin-bottom: 30px;
          padding: 13px 20px;
          font-size: 15px;
          background: #f1f1f1;
          color: #303133;
        }
        .el-form {
          padding-left: 5px;
          padding-right: 50px;
        }
      }
      .confirm-working-container {
        .el-input.is-disabled .el-input__inner {
          color: #606266 !important;
          cursor: default !important;
        }
      }
      .confirm-container {
        margin-top: 35px;
        padding-left: 105px;
        .el-button {
          width: 100px;
        }
      }
    }
  }
</style>
