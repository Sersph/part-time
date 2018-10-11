<template>
  <section class="sign-in-container">
    <el-tabs class="tab" v-model="signInType" :stretch="true">
      <el-tab-pane label="个人用户登陆" name="personal">
        <el-form :model="personalForm" :rules="personalFormRules" ref="personalForm" label-width="128px">
          <el-form-item label="用户名" prop="username">
            <el-input v-model="personalForm.username"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input type="password" v-model="personalForm.password"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" :loading="submitPersonalFormLoading" @click="submitPersonalForm('personalForm')">立即登陆</el-button>
          </el-form-item>
        </el-form>
        <p class="sign-up-tooltip">
          <span>没有账号？</span>
          <router-link to="/account/signUp?type=personal">个人用户注册</router-link>
        </p>
      </el-tab-pane>
      <el-tab-pane label="企业用户登陆" name="enterprise">
        <el-form :model="enterpriseForm" :rules="enterpriseFormRules" ref="enterpriseForm" label-width="128px">
          <el-form-item label="邮箱" prop="email">
            <el-input v-model="enterpriseForm.email"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input type="password" v-model="enterpriseForm.password"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitEnterpriseForm('enterpriseForm')">立即登陆</el-button>
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
  data () {
    return {
      signInType: this.$route.query.type !== undefined ? this.$route.query.type : 'personal',
      submitPersonalFormLoading: false,
      personalForm: {
        username: '',
        password: ''
      },
      personalFormRules: {
        username: [
          { required: true, message: '用户名由6-20个字符组成', trigger: 'change' },
          { min: 6, max: 20, message: '用户名由6-20个字符组成', trigger: 'change' }
        ],
        password: [
          { required: true, message: '密码由6-20个字符组成', trigger: 'change' },
          { min: 6, max: 20, message: '密码由6-20个字符组成', trigger: 'change' }
        ]
      },
      enterpriseForm: {},
      enterpriseFormRules: {
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
    submitPersonalForm (formName) {
      this.$refs[formName].validate(async (valid) => {
        if (valid) {
          this.submitPersonalFormLoading = true;
          NProgress.start();
          const result = await api.account.personalSignIn({
            username: this.personalForm.username,
            password: this.personalForm.password
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
            }, 1500);
          } else {
            NProgress.done();
            this.submitPersonalFormLoading = false;
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
    submitEnterpriseForm (formName) {
      this.$refs[formName].validate(async (valid) => {
        if (valid) {
          this.submitPersonalFormLoading = true;
          NProgress.start();
          const result = await api.account.enterpriseSignIn({
            email: this.enterpriseForm.email,
            password: this.enterpriseForm.password
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
            }, 1500);
          } else {
            NProgress.done();
            this.submitPersonalFormLoading = false;
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
