<template>
  <section class="sign-up-container">
    <el-tabs class="tab" v-model="signUpType" :stretch="true">
      <el-tab-pane label="个人用户注册" name="personal">
        <el-form :model="personalForm" :rules="personalFormRules" ref="personalForm" label-width="128px">
          <el-form-item label="用户名" prop="username">
            <el-input v-model="personalForm.username"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input type="password" v-model="personalForm.password"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" :loading="submitPersonalFormLoading" @click="submitPersonalForm('personalForm')">
              立即注册
            </el-button>
          </el-form-item>
        </el-form>
      </el-tab-pane>
      <el-tab-pane label="企业用户注册" name="enterprise">
        <el-form :model="enterpriseForm" :rules="enterpriseFormRules" ref="enterpriseForm" label-width="128px">
          <el-form-item label="企业名称" prop="enterpriseName">
            <el-input v-model="enterpriseForm.enterpriseName"></el-input>
          </el-form-item>
          <el-form-item label="企业所在城市" prop="cityId">
            <el-cascader
              :options="cityList"
              :props="{value: 'id', label: 'name'}"
              v-model="enterpriseForm.cityId">
            </el-cascader>
          </el-form-item>
          <el-form-item label="邮箱" prop="email">
            <el-input type="text" v-model="enterpriseForm.email"></el-input>
          </el-form-item>
          <el-form-item label="邮箱验证码" prop="emailCaptcha">
            <el-input placeholder="验证码" v-model="enterpriseForm.emailCaptcha">
              <el-button :class="sendMailCaptchaCountdown > 0 ? 'disabled' : ''" slot="append" @click="sendMailCaptcha('enterpriseForm')">
                {{ sendMailCaptchaCountdown > 0 ? `已发送 (${sendMailCaptchaCountdown}s)` : '发送' }}
              </el-button>
            </el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input type="password" v-model="enterpriseForm.password"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" :loading="submitEnterpriseFormLoading" @click="submitEnterpriseForm('enterpriseForm')">立即注册</el-button>
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
  data () {
    return {
      signUpType: this.$route.query.type !== undefined ? this.$route.query.type : 'personal',
      submitPersonalFormLoading: false,
      personalForm: {},
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
      sendMailCaptchaCountdown: 0,
      submitEnterpriseFormLoading: false,
      enterpriseForm: {
        email: '1092879991@qq.com'
      },
      enterpriseFormRules: {
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
    submitPersonalForm (formName) {
      this.$refs[formName].validate(async (valid) => {
        if (valid) {
          this.submitPersonalFormLoading = true;
          NProgress.start();
          const result = await api.account.personalSignUp({
            username: this.personalForm.username,
            password: this.personalForm.password
          });
          if (result.code === 0) {
            // 注册成功逻辑
            this.$notify({
              message: '注册成功',
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
    submitEnterpriseForm (formName) {
      this.$refs[formName].validate(async (valid) => {
        if (valid) {
          this.submitEnterpriseFormLoading = true;
          NProgress.start();
          console.log(this.enterpriseForm.cityId);
          const result = await api.account.enterpriseSignUp({
            enterpriseName: this.enterpriseForm.enterpriseName,
            cityId: this.enterpriseForm.cityId[1],
            email: this.enterpriseForm.email,
            captcha: this.enterpriseForm.emailCaptcha,
            password: this.enterpriseForm.password
          });
          if (result.code === 0) {
            // 注册成功逻辑
            this.$notify({
              message: '注册成功',
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
            this.submitEnterpriseFormLoading = false;
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
    async sendMailCaptcha (formName) {
      // 验证邮箱表单
      this.$refs[formName].validateField('email', async errorMessage => {
        if (!errorMessage) {
          if (this.sendMailCaptchaCountdown > 0) return;

          NProgress.start();
          // 发送邮件验证码
          const result = await api.account.sendMailCaptcha({
            email: this.enterpriseForm.email
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
