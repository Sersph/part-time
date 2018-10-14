<template>
  <section class="sign-in-container">
    <section class="form-container">
      <h1 class="title">Please sign in</h1>
      <el-form :model="signInForm" :rules="signInFormRules" ref="signInForm">
        <el-form-item class="username" prop="username">
          <el-input type="text" placeholder="Username" autocomplete="off" v-model="signInForm.username"></el-input>
        </el-form-item>
        <el-form-item class="password" prop="password">
          <el-input type="password" placeholder="Password" v-model="signInForm.password" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" :loading="doSignInFormLoading" @click="doSignInForm('signInForm')">Sign In
          </el-button>
        </el-form-item>
      </el-form>
      <p class="copyright">© 2017 - 2018</p>
    </section>
  </section>
</template>

<script>
import { mapActions } from 'vuex';
import NProgress from 'nprogress';
import api from '@/api';

export default {
  name: 'SignIn',
  data () {
    return {
      doSignInFormLoading: false,
      signInForm: {},
      signInFormRules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'change' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'change' }
        ]
      }
    };
  },
  methods: {
    ...mapActions('account', [
      'asyncInitAccountInfo'
    ]),
    doSignInForm (formName) {
      this.$refs[formName].validate(async (valid) => {
        if (valid) {
          this.doSignInFormLoading = true;
          NProgress.start();
          const result = await api.account.signIn({
            username: this.signInForm.username,
            password: this.signInForm.password
          });
          if (result.code === 0) {
            // 登陆成功逻辑
            this.$notify({
              message: '登陆成功',
              position: 'bottom-left',
              duration: 1500,
              showClose: false
            });
            // 更新 vuex 用户信息
            await this.asyncInitAccountInfo();
            NProgress.done();
            // 跳转首页
            setTimeout(() => {
              this.$router.replace('/');
            }, 500);
          } else {
            NProgress.done();
            this.doSignInFormLoading = false;
            // 登陆失败逻辑
            this.$notify({
              message: result.message,
              position: 'bottom-left',
              duration: 2000,
              showClose: false
            });
          }
        } else {
          return false;
        }
      });
    }
  }
};
</script>

<style lang="scss">
  .sign-in-container {
    display: flex;
    align-items: center;
    position: fixed;
    top: 0;
    left: 0;
    right: 0;
    height: 100%;
    background-color: #f5f5f5;
    .form-container {
      position: relative;
      top: -10%;
      width: 100%;
      max-width: 330px;
      margin: auto;
      padding: 15px;
      text-align: center;
      .password {
        margin-bottom: 25px;
      }
      .el-button {
        width: 100%;
      }
    }
    .copyright {
      margin-top: 3rem !important;
      font-size: 15px;
      color: #6c757d !important;
    }
  }
</style>
