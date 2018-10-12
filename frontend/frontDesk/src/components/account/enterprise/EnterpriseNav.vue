<template>
  <section class="enterprise-nav-container">
    <el-menu
      class="enterprise-nav-menu"
      :default-openeds="['1', '2']"
      :router="true">
      <el-submenu
        v-for="(enterpriseMenuItem, index) in enterpriseMenuList"
        :key="index"
        :index="enterpriseMenuItem.index">
        <template slot="title">
          <i class="material-icons">{{ enterpriseMenuItem.icon }}</i>
          <span>{{ enterpriseMenuItem.title }}</span>
        </template>
        <el-menu-item
          v-for="(enterpriseMenuChildItem, index) in enterpriseMenuItem.children"
          :key="index"
          :route="enterpriseMenuChildItem.path"
          :index="enterpriseMenuChildItem.index"
          :class="{active: activeIndex === enterpriseMenuChildItem.index}">
          <span>{{ enterpriseMenuChildItem.title }}</span>
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
      activeIndex: null,
      enterpriseMenuList: [
        {
          index: '1',
          title: '兼职管理',
          icon: 'personal',
          children: [
            { index: '1-1', title: '我的发布', path: '/account/enterprise/partTime' },
            { index: '1-2', title: '我的招聘', path: '/account/enterprise/partTimeRecruitment' }
          ]
        },
        {
          index: '2',
          title: '账户设置',
          icon: 'view_list',
          children: [
            { index: '2-1', title: '企业认证', path: '/account/enterprise/certification' }
          ]
        }
      ]
    };
  },
  mounted () {
    // 刷新菜单样式
    this.editActiveClass(this.$route);
    // 监听路由刷新菜单样式
    this.$router.beforeEach((to, from, next) => {
      this.editActiveClass(to);
      next();
    });
  },
  methods: {
    editActiveClass (route) {
      // 激活菜单样式
      this.enterpriseMenuList.find(enterpriseMenuItem => {
        if (enterpriseMenuItem.children) {
          return enterpriseMenuItem.children.find(enterpriseMenuChildItem => {
            const routerPathArr = route.path.split('/');
            const enterpriseMenuChildItemPathArr = enterpriseMenuChildItem.path.split('/');
            if (routerPathArr[1] + routerPathArr[2] + routerPathArr[3] === enterpriseMenuChildItemPathArr[1] + enterpriseMenuChildItemPathArr[2] + enterpriseMenuChildItemPathArr[3]) {
              this.activeIndex = enterpriseMenuChildItem.index;
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
