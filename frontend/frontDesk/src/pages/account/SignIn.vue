<template>
  <section class="sign-in-container">
    <el-tabs class="tab" v-model="signInType" :stretch="true">
      <el-tab-pane label="个人用户登陆" name="personal">
        <el-form :model="personalSignInForm" :rules="personalSignInFormRules" ref="personalSignInForm"
                 label-width="128px">
          <el-form-item label="用户名" prop="username">
            <el-input v-model="personalSignInForm.username"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input type="password" v-model="personalSignInForm.password"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" :loading="doPersonalSignInLoading"
                       @click="doPersonalSignIn('personalSignInForm')">立即登陆
            </el-button>
          </el-form-item>
        </el-form>
        <p class="sign-up-tooltip">
          <span>没有账号？</span>
          <router-link to="/account/signUp?type=personal">个人用户注册</router-link>
        </p>
      </el-tab-pane>
      <el-tab-pane label="企业用户登陆" name="enterprise">
        <el-form :model="enterpriseSignInForm" :rules="enterpriseSignInFormRules" ref="enterpriseSignInForm"
                 label-width="128px">
          <el-form-item label="邮箱" prop="email">
            <el-input v-model="enterpriseSignInForm.email"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input type="password" v-model="enterpriseSignInForm.password"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" :loading="doEnterpriseSignInLoading"
                       @click="doEnterpriseSignIn('enterpriseSignInForm')">立即登陆
            </el-button>
          </el-form-item>
        </el-form>
        <p class="sign-up-tooltip">
          <span>没有账号？</span>
          <router-link to="/account/signUp?type=enterprise">企业用户注册</router-link>
        </p>
      </el-tab-pane>
    </el-tabs>
  </section>
</template>

<script>
import { mapActions } from 'vuex';
import NProgress from 'nprogress';
import api from '@/api';

export default {
  name: 'SignIn',
  data() {
    return {
      signInType: this.$route.query.type !== undefined ? this.$route.query.type : 'personal',
      doPersonalSignInLoading: false,
      personalSignInForm: {
        username: '',
        password: ''
      },
      personalSignInFormRules: {
        username: [
          { required: true, message: '用户名由6-20个字符组成', trigger: 'change' },
          { min: 6, max: 20, message: '用户名由6-20个字符组成', trigger: 'change' }
        ],
        password: [
          { required: true, message: '密码由6-20个字符组成', trigger: 'change' },
          { min: 6, max: 20, message: '密码由6-20个字符组成', trigger: 'change' }
        ]
      },
      doEnterpriseSignInLoading: false,
      enterpriseSignInForm: {},
      enterpriseSignInFormRules: {
        email: [
          { required: true, message: '请输入正确的邮箱', trigger: 'change' },
          { type: 'email', message: '请输入正确的邮箱', trigger: 'change' }
        ],
        password: [
          { required: true, message: '密码由6-20个字符组成', trigger: 'change' },
          { min: 6, max: 20, message: '密码由6-20个字符组成', trigger: 'change' }
        ]
      }
    };
  },
  methods: {
    ...mapActions('account', [
      'asyncInitAccountInfo'
    ]),
    doPersonalSignIn(formName) {
      this.$refs[formName].validate(async (valid) => {
        if (valid) {
          this.doPersonalSignInLoading = true;
          NProgress.start();
          const result = await api.account.personalSignIn({
            username: this.personalSignInForm.username,
            password: this.personalSignInForm.password
          });
          if (result.code === 0) {
            // 登陆成功逻辑
            // 保存 token
            window.localStorage.setItem('access_token', result.access_token);
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
            }, 1500);
          } else {
            NProgress.done();
            this.doPersonalSignInLoading = false;
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
    },
    doEnterpriseSignIn(formName) {
      this.$refs[formName].validate(async (valid) => {
        if (valid) {
          this.doEnterpriseSignInLoading = true;
          NProgress.start();
          const result = await api.account.enterpriseSignIn({
            username: this.enterpriseSignInForm.email,
            password: this.enterpriseSignInForm.password
          });
          if (result.code === 0) {
            // 登陆成功逻辑
            // 保存 token
            window.localStorage.setItem('access_token', result.access_token);
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
            }, 1500);
          } else {
            NProgress.done();
            this.doEnterpriseSignInLoading = false;
            // 登陆失败逻辑
            this.$notify({
              message: result.message,
              position: 'bottom-left',
              duration: 2000,
              showClose: false
            });
          }
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

</style>
