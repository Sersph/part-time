<template>
  <section class="enterprise-nav-container">
    <el-menu
      class="enterprise-nav-menu"
      :default-openeds="['1', '2']"
      :router="true">
      <el-submenu
        v-for="(enterpriseMenuItem, index) in enterpriseMenuList"
        :key="index"
        :index="enterpriseMenuItem.meta.index">
        <template slot="title">
          <i class="material-icons">{{ enterpriseMenuItem.meta.icon }}</i>
          <span>{{ enterpriseMenuItem.meta.name }}</span>
        </template>
        <el-menu-item
          v-for="(enterpriseMenuChildItem, index) in enterpriseMenuItem.children"
          :key="index"
          :route="enterpriseMenuChildItem.path"
          :index="enterpriseMenuChildItem.meta.index"
          :class="{active: enterpriseMenuActiveIndex === enterpriseMenuChildItem.meta.index}">
          <span>{{ enterpriseMenuChildItem.meta.name }}</span>
        </el-menu-item>
      </el-submenu>
    </el-menu>
  </section>
</template>

<script>
export default {
  name: 'EnterpriseNav',
  data() {
    return {
      enterpriseMenuActiveIndex: null,
      enterpriseMenuList: []
    };
  },
  mounted() {
    // 刷新菜单列表
    this.$router.options.routes.find(routeItem => {
      if (routeItem.path === '/account') {
        routeItem.children.find(routerChildItem => {
          if (routerChildItem.path === '/account/enterprise') {
            // 获取企业的路由列表
            this.enterpriseMenuList = routerChildItem.children;
            return true;
          }
        });
        return true;
      }
    });
    // 刷新菜单样式
    this.editEnterpriseMenuActiveIndex(this.$route);
    // 监听路由刷新菜单样式
    this.$router.beforeEach((to, from, next) => {
      this.editEnterpriseMenuActiveIndex(to);
      next();
    });
  },
  methods: {
    editEnterpriseMenuActiveIndex(route) {
      // 激活菜单样式
      this.enterpriseMenuList.find(enterpriseMenuItem => {
        if (enterpriseMenuItem.children) {
          return enterpriseMenuItem.children.find(enterpriseMenuChildItem => {
            const routerPathArr = route.path.split('/');
            const enterpriseMenuChildItemPathArr = enterpriseMenuChildItem.path.split('/');
            if (routerPathArr[1] + routerPathArr[2] + routerPathArr[3] + routerPathArr[4] ===
              enterpriseMenuChildItemPathArr[1] + enterpriseMenuChildItemPathArr[2] + enterpriseMenuChildItemPathArr[3] + enterpriseMenuChildItemPathArr[4]) {
              this.enterpriseMenuActiveIndex = enterpriseMenuChildItem.meta.index;
              return true;
            }
          });
        }
      });
    }
  }
};
</script>

<style lang="scss">
  .enterprise-nav-container {
    min-width: 251px;
    min-height: 100vh;
    .enterprise-nav-menu {
      height: 100%;
      background-color: #f5f5f5;
      box-shadow: 0 0 15px rgba(0, 0, 0, 0.2);
      .el-menu-item {
        padding-left: 55px !important;
      }
      .el-submenu__title,
      .el-menu-item {
        background-color: #f5f5f5;
      }
      .el-submenu__title:hover,
      .el-menu-item:hover {
        background: #dcdcdc;
      }
      .el-menu-item span {
        transition: color .3s;
      }
      .is-active {
        color: #303133;
      }
      .active {
        color: #409EFF;
        background: #dcdcdc;
      }
      .material-icons {
        width: 25px;
        height: 25px;
        margin-right: 10px;
      }
    }
  }
</style>
