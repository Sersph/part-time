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
    <footer-nav/>
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

  body {
    overflow-y: scroll;
  }

  a {
    text-decoration: none;
  }

  .opacity-enter-active,
  .opacity-leave-active {
    transition: opacity 150ms ease-in-out;
  }

  .opacity-enter,
  .opacity-leave-active {
    opacity: 0;
  }
</style>
