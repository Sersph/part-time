<template>
  <section id="app">
    <!-- 顶部菜单组件 -->
    <header-nav/>
    <main class="wrapper-container" ref="wrapper-container">
      <transition name="slide-bottom" mode="out-in">
        <router-view/>
      </transition>
    </main>
  </section>
</template>

<script>
import { mapState, mapActions } from 'vuex';
import NProgress from 'nprogress';
import HeaderNav from '@/components/header/HeaderNav';

export default {
  name: 'App',
  components: {
    HeaderNav
  },
  data() {
    return {
      transitionName: '',
      complete: false
    };
  },
  computed: {
    ...mapState('account', [
      'accountInfo'
    ])
  },
  watch: {
    // 深度监听 route 判断登陆权限, 防止路由器后退操作
    $route: {
      handler() {
        this.checkSignIn(this.$route);
      }
    }
  },
  async created() {
    NProgress.start();
    // 初始化地区
    const initRegionListPromise = this.initRegionList();
    // 初始化兼职基本信息
    const initPartTimeBaseInfoPromise = this.asyncInitPartTimeBaseInfo();
    // 初始化用户信息
    const asyncInitAccountInfoPromise = this.asyncInitAccountInfo();

    await initRegionListPromise;
    await initPartTimeBaseInfoPromise;
    await asyncInitAccountInfoPromise;
    NProgress.done();

    // 判断登陆权限
    this.checkSignIn(this.$route);
    // 监听路由判断登陆权限
    this.$router.beforeEach((to, from, next) => {
      this.checkSignIn(to, 1);
      next();
    });
  },
  methods: {
    ...mapActions('location', [
      'initRegionList'
    ]),
    ...mapActions('account', [
      'asyncInitAccountInfo'
    ]),
    ...mapActions('partTime', [
      'asyncInitPartTimeBaseInfo'
    ]),
    checkSignIn(route) {
      // 验证路由是否需要登陆才能访问
      if (route.meta.needSignIn) {
        const type = route.path.indexOf('/account/enterprise') !== -1 ? 2 : 3;
        // 验证路由是否需要企业登陆还是个人用户登陆
        if (!this.accountInfo.id || this.accountInfo.roleId !== type) {
          // 未登录跳转登陆页面
          this.$router.push(`/account/signIn?type=${type === 2 ? 'enterprise' : 'personal'}`);
        }
      }
    }
  }
};
</script>

<style lang="scss">
  input::-webkit-outer-spin-button,
  input::-webkit-inner-spin-button {
    -webkit-appearance: none;
  }

  input[type="number"] {
    -moz-appearance: textfield;
  }

  body {
    overflow-y: scroll;
  }

  a {
    text-decoration: none;
  }

  p {
    margin: 0;
  }

  #app {
    min-width: 1000px;
    .wrapper-container {
      width: 100%;
      & > :first-child {
        width: 79%;
        margin-left: auto;
        margin-right: auto;
      }
    }
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

  .opacity-enter-active,
  .opacity-leave-active {
    transition: opacity .5s cubic-bezier(0.0, 0.0, 0.2, 1), transform .5s cubic-bezier(0.0, 0.0, 0.2, 1);
  }

  .opacity-enter,
  .opacity-leave-active {
    opacity: 0;
  }

  .el-form-item.is-success .el-input__inner, .el-form-item.is-success .el-input__inner:focus, .el-form-item.is-success .el-textarea__inner, .el-form-item.is-success .el-textarea__inner:focus {
    border-color: #409eff !important;
  }

  .el-form-item {
    margin-bottom: 31px !important;
  }

  .el-select,
  .el-cascader,
  .el-date-editor {
    width: 100% !important;
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
    background: #0425c0 !important;;
    box-shadow: 0 0 10px rgba(17, 255, 242, 0.7) !important;;
  }
</style>
