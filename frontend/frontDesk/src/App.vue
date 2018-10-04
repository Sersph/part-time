<template>
  <section id="app">
    <header-nav/>
    <main class="wrapper-container" :span="20">
      <transition :name="transitionName">
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
      transitionName: '',
      transitionFirstFlag: true
    };
  },
  watch: {
    $route (to, from) {
      if (this.transitionFirstFlag) {
        this.transitionFirstFlag = false;
        return;
      }
      if (to.meta.index > from.meta.index) {
        this.transitionName = 'slide-left';
      } else {
        this.transitionName = 'slide-right';
      }
    }
  }
};
</script>

<style lang="scss">
  #app {
    .wrapper-container {
      position: absolute;
      top: 60px;
      right: 0;
      left: 0;
      width: 83.33333%;
      margin-left: auto;
      margin-right: auto;
    }
  }

  a {
    text-decoration: none;
  }

  .slide-left-enter-active,
  .slide-left-leave-active,
  .slide-right-enter-active,
  .slide-right-leave-active {
    position: absolute;
    will-change: transform;
    transition: transform .5s;
  }

  .slide-left-enter {
    transform: translate3d(150%, 0, 0);
  }

  .slide-left-leave-active {
    transform: translate3d(-150%, 0, 0);
  }

  .slide-right-enter {
    transform: translate3d(-150%, 0, 0);
  }

  .slide-right-leave-active {
    transform: translate3d(150%, 0, 0);
  }
</style>
