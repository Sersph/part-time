<template>
  <div id="app">
    <transition name="slide-bottom" mode="out-in">
      <router-view/>
    </transition>
  </div>
</template>

<script>
import { mapState, mapActions } from 'vuex';

export default {
  name: 'App',
  computed: {
    ...mapState('account', [
      'accountInfo'
    ])
  },
  async created () {
    // 初始化用户信息
    await this.asyncInitAccountInfo();
    // 判断登陆权限
    this.checkSignIn(this.$route);
  },
  methods: {
    ...mapActions('account', [
      'asyncInitAccountInfo'
    ]),
    checkSignIn (route) {
      // 验证路由是否需要登陆才能访问
      if (route.meta.needSignIn) {
        if (!this.accountInfo.id) {
          // 未登录跳转登陆页面
          this.$router.push('/account/signIn');
        }
      }
    }
  }
};
</script>

<style lang="scss">
  html {
    overflow-x: hidden;
  }

  html,
  #app,
  body {
    height: 100%;
  }

  .slide-bottom-enter-active,
  .slide-bottom-leave-active {
    transition: opacity .3s cubic-bezier(0.0, 0.0, 0.2, 1), transform .3s cubic-bezier(0.0, 0.0, 0.2, 1);
  }

  .slide-bottom-enter,
  .slide-bottom-leave-active {
    opacity: 0;
    transform: translateY(15vh);
  }

  .slide-left-enter-active,
  .slide-left-leave-active {
    transition: opacity .3s cubic-bezier(0.0, 0.0, 0.2, 1), transform .3s cubic-bezier(0.0, 0.0, 0.2, 1);
  }

  .slide-left-enter,
  .slide-left-leave-active {
    opacity: 0;
    transform: translateX(5vh);
  }

  .el-form-item.is-success .el-input__inner, .el-form-item.is-success .el-input__inner:focus, .el-form-item.is-success .el-textarea__inner, .el-form-item.is-success .el-textarea__inner:focus {
    border-color: #409eff !important;
  }

  .el-select,
  .el-cascader {
    width: 100%;
  }

  .el-notification {
    border: 0 !important;
    border-radius: 2px !important;
    background-color: #323232 !important;
    box-shadow: 0 6px 10px 0 rgba(0, 0, 0, 0.14), 0 1px 18px 0 rgba(0, 0, 0, 0.12), 0 3px 5px -1px rgba(0, 0, 0, 0.2) !important;
  }

  .el-icon-loading {
    display: none !important;
  }

  .el-button [class*=el-icon-] + span {
    margin-left: 0 !important;
  }

  .el-notification__content {
    margin: 0 !important;
    color: #fff !important;
  }

  // nprogress
  #nprogress .bar {
    height: 5px !important;
    background: #0168e6 !important;;
    box-shadow: 0 0 10px rgba(17, 255, 242, 0.7) !important;;
  }
</style>
