<template>
  <section class="profile-resume-container">
    <el-form :model="profileResumeForm" :rules="profileResumeFormRules" ref="profileResumeForm" label-width="128px">
      <el-form-item label="用户名" prop="username">
        <el-input v-model="profileResumeForm.username"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input v-model="profileResumeForm.password"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitProfileForm('profileResumeForm')">立即注册</el-button>
      </el-form-item>
    </el-form>
  </section>
</template>

<script>
export default {
  name: 'ProfileResume',
  data () {
    return {
      profileResumeForm: {},
      profileResumeFormRules: {
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
    submitProfileForm (formName) {
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
  .profile-resume-container {
    padding: 56px 0 0 0;
    .el-form {
      max-width: 500px;
    }
  }
</style>
