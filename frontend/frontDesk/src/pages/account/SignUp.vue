<template>
  <section class="sign-up-container">
    <el-tabs class="tab" v-model="signUpType" :stretch="true">
      <el-tab-pane label="个人用户注册" name="personal">
        <el-form :model="personalSignUpForm" :rules="personalSignUpFormRules" ref="personalSignUpForm"
                 label-width="128px">
          <el-form-item label="用户名" prop="username">
            <el-input v-model="personalSignUpForm.username"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input type="password" v-model="personalSignUpForm.password"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" :loading="doPersonalSignUpLoading"
                       @click="doPersonalSignUp('personalSignUpForm')">
              立即注册
            </el-button>
          </el-form-item>
        </el-form>
      </el-tab-pane>
      <el-tab-pane label="企业用户注册" name="enterprise">
        <el-form :model="enterpriseSignUpForm" :rules="enterpriseSignUpFormRules" ref="enterpriseSignUpForm"
                 label-width="128px">
          <el-form-item label="企业名称" prop="enterpriseName">
            <el-input v-model="enterpriseSignUpForm.enterpriseName"></el-input>
          </el-form-item>
          <el-form-item label="企业所在城市" prop="cityId">
            <el-cascader
              :options="cityList"
              :props="{value: 'id', label: 'name'}"
              v-model="enterpriseSignUpForm.cityId">
            </el-cascader>
          </el-form-item>
          <el-form-item label="邮箱" prop="email">
            <el-input type="text" v-model="enterpriseSignUpForm.email"></el-input>
          </el-form-item>
          <el-form-item label="邮箱验证码" prop="emailCaptcha">
            <el-input placeholder="验证码" v-model="enterpriseSignUpForm.emailCaptcha">
              <el-button :class="sendMailCaptchaCountdown > 0 ? 'disabled' : ''" slot="append"
                         @click="sendMailCaptcha('enterpriseSignUpForm')">
                {{ sendMailCaptchaCountdown > 0 ? `已发送 (${sendMailCaptchaCountdown}s)` : '发送' }}
              </el-button>
            </el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input type="password" v-model="enterpriseSignUpForm.password"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" :loading="doEnterpriseSignUpLoading"
                       @click="doEnterpriseSignUp('enterpriseSignUpForm')">立即注册
            </el-button>
          </el-form-item>
        </el-form>
      </el-tab-pane>
    </el-tabs>
  </section>
</template>

<script>
import { mapActions, mapGetters } from 'vuex';
import NProgress from 'nprogress';
import api from '@/api';

export default {
  name: 'SignUp',
  data() {
    return {
      signUpType: this.$route.query.type !== undefined ? this.$route.query.type : 'personal',
      doPersonalSignUpLoading: false,
      personalSignUpForm: {},
      personalSignUpFormRules: {
        username: [
          { required: true, message: '用户名由6-20个字符组成', trigger: 'change' },
          { min: 6, max: 20, message: '用户名由6-20个字符组成', trigger: 'change' }
        ],
        password: [
          { required: true, message: '密码由6-20个字符组成', trigger: 'change' },
          { min: 6, max: 20, message: '密码由6-20个字符组成', trigger: 'change' }
        ]
      },
      sendMailCaptchaCountdown: 0,
      doEnterpriseSignUpLoading: false,
      enterpriseSignUpForm: {},
      enterpriseSignUpFormRules: {
        enterpriseName: [
          { required: true, message: '企业名称由2-20个中文、英文、数字及合法字符组成', trigger: 'change' },
          { min: 2, max: 20, message: '企业名称由2-20个中文、英文、数字及合法字符组成', trigger: 'change' }
        ],
        cityId: [
          { required: true, message: '请选择企业所在城市', trigger: 'change' }
        ],
        email: [
          { required: true, message: '请输入正确的邮箱', trigger: 'change' },
          { type: 'email', message: '请输入正确的邮箱', trigger: 'change' }
        ],
        emailCaptcha: [
          { required: true, message: '请输入邮箱验证码', trigger: 'change' },
          { min: 6, max: 6, message: '验证码格式不对', trigger: 'change' }
        ],
        password: [
          { required: true, message: '密码由6-20个字符组成', trigger: 'change' },
          { min: 6, max: 20, message: '密码由6-20个字符组成', trigger: 'change' }
        ]
      }
    };
  },
  computed: {
    ...mapGetters('location', [
      'cityList'
    ])
  },
  methods: {
    ...mapActions('account', [
      'asyncInitAccountInfo'
    ]),
    doPersonalSignUp(formName) {
      this.$refs[formName].validate(async (valid) => {
        if (valid) {
          this.doPersonalSignUpLoading = true;
          NProgress.start();
          const result = await api.account.personalSignUp({
            username: this.personalSignUpForm.username,
            password: this.personalSignUpForm.password
          });
          if (result.code === 0) {
            // 注册成功逻辑
            // 请求登陆接口, 自动登陆
            const result = await api.account.personalSignIn({
              username: this.personalSignUpForm.username,
              password: this.personalSignUpForm.password
            });
            // 保存 token
            window.localStorage.setItem('access_token', result.access_token);
            // 更新 vuex 用户信息
            await this.asyncInitAccountInfo();
            this.$notify({
              message: '注册成功',
              position: 'bottom-left',
              duration: 1500,
              showClose: false
            });
            NProgress.done();
            // 跳转首页
            setTimeout(() => {
              this.$router.replace('/');
            }, 1500);
          } else {
            NProgress.done();
            this.doPersonalSignUpLoading = false;
            // 注册失败逻辑
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
    doEnterpriseSignUp(formName) {
      this.$refs[formName].validate(async (valid) => {
        if (valid) {
          this.doEnterpriseSignUpLoading = true;
          NProgress.start();
          const result = await api.account.enterpriseSignUp({
            username: this.enterpriseSignUpForm.email,
            name: this.enterpriseSignUpForm.enterpriseName,
            cityId: this.enterpriseSignUpForm.cityId[1],
            email: this.enterpriseSignUpForm.email,
            captcha: this.enterpriseSignUpForm.emailCaptcha,
            password: this.enterpriseSignUpForm.password
          });
          if (result.code === 0) {
            // 注册成功逻辑
            // 请求登陆接口, 自动登陆
            const result = await api.account.enterpriseSignIn({
              username: this.enterpriseSignUpForm.email,
              password: this.enterpriseSignUpForm.password
            });
            // 保存 token
            window.localStorage.setItem('access_token', result.access_token);
            // 更新 vuex 用户信息
            await this.asyncInitAccountInfo();
            // 提示
            this.$notify({
              message: '注册成功',
              position: 'bottom-left',
              duration: 1500,
              showClose: false
            });
            NProgress.done();
            // 跳转首页
            setTimeout(() => {
              this.$router.replace('/');
            }, 1500);
          } else {
            NProgress.done();
            this.doEnterpriseSignUpLoading = false;
            // 注册失败逻辑
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
    },
    async sendMailCaptcha(formName) {
      // 验证邮箱表单
      this.$refs[formName].validateField('email', async errorMessage => {
        if (!errorMessage) {
          if (this.sendMailCaptchaCountdown > 0) return;

          NProgress.start();
          // 发送邮件验证码
          const result = await api.account.sendMailCaptcha({
            email: this.enterpriseSignUpForm.email
          });
          NProgress.done();

          // 倒计时
          this.sendMailCaptchaCountdown = 30;
          this.sendMailCaptchaInterval = setInterval(() => {
            this.sendMailCaptchaCountdown--;
            if (this.sendMailCaptchaCountdown === 0) {
              clearInterval(this.sendMailCaptchaInterval);
            }
          }, 1000);

          // 发送失败提示
          if (result.code !== 0) {
            clearInterval(this.sendMailCaptchaInterval);
            this.sendMailCaptchaCountdown = 0;
            this.$notify({
              message: result.message,
              position: 'bottom-left',
              duration: 2000,
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
  .sign-up-container {
    .el-input-group__append {
      padding: 0;
      button.el-button {
        background: #409EFF;
        border-color: #409EFF;
      }
      button.disabled {
        background-color: #dcdfe6;
        border-color: #dcdfe6;
        cursor: default;
      }
      button.disabled:hover {
        background-color: #dcdfe6;
        border-color: #dcdfe6;
      }
    }

    .el-notification__content {
      margin: 0 !important;
      color: #fff !important;
    }

    .el-input-group__append {
      background: transparent;
      border-color: transparent;
      color: #fff;
    }
    .el-input-group__append {
      button {
        width: 79px;
        height: 40px;
        margin: -1px 0 0 -1px;
        padding: 0 5px;
        border-radius: 0;
        border-top-right-radius: 4px;
        border-bottom-right-radius: 4px;
        font-size: 11px;
        background-color: #409EFF;
        border-color: #409EFF;
      }
      button:hover {
        background: #66b1ff;
        border-color: #66b1ff;
      }
      button:active {
        background: #3a8ee6;
        border-color: #3a8ee6;
        color: #fff;
      }
    }
  }
</style>
