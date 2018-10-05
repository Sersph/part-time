<template>
  <section id="app">
    <!-- 顶部菜单组件 -->
    <header-nav/>
    <main class="wrapper-container">
      <transition name="opacity" mode="out-in">
        <router-view/>
      </transition>
    </main>
    <!-- 底部菜单组件 -->
    <footer-nav
      v-show="this.$route.meta.showFooterNav"
    />
  </section>
</template>

<script>
import HeaderNav from '@/components/header/HeaderNav';
import FooterNav from '@/components/footer/FooterNav';

export default {
  name: 'App',
  components: {
    HeaderNav,
    FooterNav
  },
  data () {
    return {
      transitionName: '',
      transitionFirstFlag: true
    };
  }
};
</script>

<style lang="scss">
  body {
    overflow-y: scroll;
  }

  a {
    text-decoration: none;
  }

  #app {
    .wrapper-container {
      width: 100%;
      min-height: 100vh;
      & > :first-child {
        width: 83.33333%;
        margin-left: auto;
        margin-right: auto;
      }
    }
  }

  .opacity-enter-active,
  .opacity-leave-active {
    transition: opacity 150ms ease-in-out;
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
  .el-cascader {
    width: 100%;
  }
</style>
