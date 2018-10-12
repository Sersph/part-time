<template>
  <el-container>
    <el-aside width="201px">
      <sidebar-nav/>
    </el-aside>
    <el-container>
      <el-header>
        <header-nav/>
      </el-header>
      <el-main>
        <transition name="slide-bottom" mode="out-in">
          <router-view/>
        </transition>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import HeaderNav from '@/components/header/HeaderNav';
import SidebarNav from '@/components/sidebar/SidebarNav';

export default {
  name: 'Master',
  components: {
    HeaderNav,
    SidebarNav
  }
};
</script>

<style lang="scss">
  .el-container,
  .el-aside,
  .el-aside ul {
    height: 100%;
  }

  .el-container {
    .el-aside {
      position: fixed;
      top: 0;
      left: 0;
      height: 100%;
    }
    .el-container {
      margin-left: 201px;
      min-height: 100%;
      .el-header {
        border-bottom: 1px solid #ddd;
      }
    }
  }
</style>
