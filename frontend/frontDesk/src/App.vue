<template>
  <section id="app">
    <!-- 顶部菜单组件 -->
    <header-nav/>
    <main class="wrapper-container" ref="wrapper-container">
      <transition name="opacity" mode="out-in">
        <router-view/>
      </transition>
    </main>
  </section>
</template>

<script>
import HeaderNav from '@/components/header/HeaderNav';

export default {
  name: 'App',
  components: {
    HeaderNav
  },
  data () {
    return {
      transitionName: ''
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
      & > :first-child {
        width: 83.33333%;
        margin-left: auto;
        margin-right: auto;
      }
    }
  }

  .opacity-enter-active,
  .opacity-leave-active {
    transition: opacity .3s cubic-bezier(0.0, 0.0, 0.2, 1), transform .3s cubic-bezier(0.0, 0.0, 0.2, 1);
  }

  .opacity-enter,
  .opacity-leave-active {
    opacity: 0;
    transform: translateY(15%);
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

  // nprogress
  #nprogress .bar {
    height: 5px !important;
    background: #0425c0 !important;;
    box-shadow: 0 0 10px rgba(17, 255, 242, 0.7) !important;;
  }
</style>
