<template>
  <section class="personal-nav-container">
    <el-menu
      class="personal-nav-menu"
      :default-openeds="['1', '2']"
      :router="true">
      <el-submenu
        v-for="(personalMenuItem, index) in personalMenuList"
        :key="index"
        :index="personalMenuItem.meta.index">
        <template slot="title">
          <i class="material-icons">{{ personalMenuItem.meta.icon }}</i>
          <span>{{ personalMenuItem.meta.name }}</span>
        </template>
        <el-menu-item
          v-for="(personalMenuChildrenItem, index) in personalMenuItem.children"
          :key="index"
          :route="personalMenuChildrenItem.path"
          :index="personalMenuChildrenItem.meta.index"
          :class="{active: personalMenuActiveIndex === personalMenuChildrenItem.meta.index}">
          <span>{{ personalMenuChildrenItem.meta.name }}</span>
        </el-menu-item>
      </el-submenu>
    </el-menu>
  </section>
</template>

<script>
export default {
  name: 'EnterpriseNav',
  data () {
    return {
      personalMenuActiveIndex: null,
      personalMenuList: []
    };
  },
  mounted () {
    // 刷新菜单列表
    this.$router.options.routes.find(routeItem => {
      if (routeItem.path === '/account') {
        routeItem.children.find(routerChildItem => {
          if (routerChildItem.path === '/account/personal') {
            // 获取企业的路由列表
            this.personalMenuList = routerChildItem.children;
            return true;
          }
        });
        return true;
      }
    });
    // 刷新菜单样式
    this.editPersonalMenuActiveIndex(this.$route);
    // 监听路由刷新菜单样式
    this.$router.beforeEach((to, from, next) => {
      this.editPersonalMenuActiveIndex(to);
      next();
    });
  },
  methods: {
    editPersonalMenuActiveIndex (route) {
      // 激活菜单样式
      this.personalMenuList.find(personalMenuItem => {
        if (personalMenuItem.children) {
          return personalMenuItem.children.find(personalMenuChildrenItem => {
            const routerPathArr = route.path.split('/');
            const personalMenuChildrenItemPathArr = personalMenuChildrenItem.path.split('/');
            if (routerPathArr[1] + routerPathArr[2] + routerPathArr[3] + routerPathArr[4] ===
              personalMenuChildrenItemPathArr[1] + personalMenuChildrenItemPathArr[2] + personalMenuChildrenItemPathArr[3] + personalMenuChildrenItemPathArr[4]) {
              this.personalMenuActiveIndex = personalMenuChildrenItem.meta.index;
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
  .personal-nav-container {
    min-width: 251px;
    min-height: 100vh;
    .personal-nav-menu {
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
