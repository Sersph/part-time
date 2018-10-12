<template>
  <section class="enterprise-certification-container">
    <el-form :model="enterpriseCertificationForm" :rules="enterpriseCertificationFormRules" ref="enterpriseCertificationForm" label-width="128px">
      <el-form-item label="用户名" prop="username">
        <el-input v-model="enterpriseCertificationForm.username"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input v-model="enterpriseCertificationForm.password"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitEnterpriseCertificationForm('enterpriseCertificationForm')">立即注册</el-button>
      </el-form-item>
    </el-form>
  </section>
</template>

<script>
export default {
  name: 'EnterpriseCertification',
  data () {
    return {
      enterpriseCertificationForm: {},
      enterpriseCertificationFormRules: {
        username: [
          { required: true, message: '用户名由3-20个字符组成', trigger: 'change' },
          { min: 3, max: 20, message: '用户名由3-20个字符组成', trigger: 'change' }
        ],
        password: [
          { required: true, message: '密码由3-20个字符组成', trigger: 'change' },
          { min: 3, max: 20, message: '密码由3-20个字符组成', trigger: 'change' }
        ]
      }
    };
  },
  methods: {
    submitEnterpriseCertificationForm (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          alert('submit p!');
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    }
  }
};
</script>

<style lang="scss">
  .enterprise-certification-container {
    .el-form {
      max-width: 500px;
    }
  }
</style>
