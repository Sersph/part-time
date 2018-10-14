<template>
  <el-menu
    :default-openeds="['1', '2']"
    :router="true">
    <el-submenu
      v-for="(sidebarMenuItem, index) in sidebarMenuList"
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
        :class="{'active': sidebarMenuChildItem.meta.index === sidebarMenuActiveIndex}">
        <span>{{ sidebarMenuChildItem.meta.name }}</span>
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
      sidebarMenuActiveIndex: null
    };
  },
  computed: {
    ...mapState('permission', [
      'sidebarMenuList'
    ])
  },
  mounted () {
    // 刷新菜单列表
    this.editSidebarMenuList({
      sidebarMenuList: this.$router.options.routes.filter(routeItem => {
        return routeItem.meta.isMasterPage;
      })
    });
    // 刷新菜单样式
    this.editSidebarMenuActiveIndex(this.$route);
    // 监听路由刷新菜单样式
    this.$router.beforeEach((to, from, next) => {
      this.editSidebarMenuActiveIndex(to);
      next();
    });
  },
  methods: {
    ...mapActions('permission', [
      'editSidebarMenuList'
    ]),
    editSidebarMenuActiveIndex (route) {
      NProgress.start();
      // 激活菜单样式
      this.sidebarMenuList.find(routeItem => {
        if (routeItem.children) {
          return routeItem.children.find(routeChildrenItem => {
            const routerPathArr = route.path.split('/');
            const routeChildrenItemPathArr = routeChildrenItem.path.split('/');
            if (routerPathArr[1] + routerPathArr[2] ===
              routeChildrenItemPathArr[1] + routeChildrenItemPathArr[2]) {
              this.sidebarMenuActiveIndex = routeChildrenItem.meta.index;
              return true;
            }
          });
        }
      });
      NProgress.done();
    }
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
