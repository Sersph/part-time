<template>
  <section class="account-container">
    <transition name="opacity" mode="out-in">
      <router-view/>
    </transition>
  </section>
</template>

<script>
export default {
  name: 'Account'
};
</script>

<style lang="scss">
  .sign-up-container,
  .sign-in-container {
    display: flex;
    justify-content: center;
    .tab {
      width: 51%;
      .el-tabs__content {
        width: 85%;
        margin: 50px auto 0;
      }
      .el-tabs__item {
        height: 80px;
        line-height: 80px;
        font-size: 15px;
      }
      .el-tabs__nav-wrap::after,
      .el-tabs__active-bar {
        height: 6px;
      }
    }
    .sign-up-tooltip {
      position: relative;
      left: 128px;
      bottom: 0;
      padding-left: 2px;
      font-size: 15px;
      span {
        color: #606266;
      }
      a {
        color: #409EFF;
      }
    }
  }
</style>
