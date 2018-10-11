<template>
  <el-menu
    :default-openeds="['1', '2']"
    :router="true">
    <el-submenu
      v-for="(sidebarMenuItem, index) in routerList"
      :key="index"
      :index="sidebarMenuItem.meta.index">
      <template slot="title">
        <i class="material-icons">{{ sidebarMenuItem.meta.icon }}</i>
        <span>{{ sidebarMenuItem.meta.name }}</span>
      </template>
      <el-menu-item
        v-for="(sidebarMenuChildItem, index) in sidebarMenuItem.children"
        :key="index"
        :route="sidebarMenuChildItem.path"
        :index="sidebarMenuChildItem.meta.index"
        :class="{'active': sidebarMenuChildItem.meta.index === activeIndex}">
        <span :class="{active: $route.path === sidebarMenuChildItem.path ? 'active': ''}">{{ sidebarMenuChildItem.meta.name }}</span>
      </el-menu-item>
    </el-submenu>
  </el-menu>
</template>

<script>
import { mapState, mapActions } from 'vuex';
import NProgress from 'nprogress';

export default {
  name: 'SidebarNav',
  data () {
    return {
      activeIndex: null
    };
  },
  computed: {
    ...mapState('permission', [
      'routerList'
    ])
  },
  watch: {
    $route (to, from) {
      NProgress.start();
      // 激活菜单样式
      this.routerList.find(sidebarMenuItem => {
        if (sidebarMenuItem.children) {
          return sidebarMenuItem.children.find(sidebarMenuChildItem => {
            if (to.path.indexOf(sidebarMenuChildItem.path) > -1) {
              this.activeIndex = sidebarMenuChildItem.meta.index;
              return true;
            }
          });
        }
      });
      NProgress.done();
    }
  },
  created () {
    // 初始化路由列表
    this.editRouterList({
      routerList: this.$router.options.routes
    });
  },
  methods: {
    ...mapActions('permission', [
      'editRouterList',
      'editCurrentRouter'
    ])
  }
};
</script>

<style lang="scss">
  .el-menu {
    background-color: #304156;
    .material-icons {
      width: 24px;
      height: 24px;
      margin-right: 15px;
    }
    .el-menu-item {
      padding-left: 59px !important;
      transition: color .3s;
    }
    .el-submenu__title,
    .el-menu-item {
      background-color: #304156;
      color: rgb(191, 203, 217);
    }
    .el-submenu__title:hover,
    .el-menu-item:hover {
      background-color: #263445 !important;
    }
    .el-menu-item.active {
      color: #409eff;
    }
  }
</style>
