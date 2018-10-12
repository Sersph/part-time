<template>
  <section class="personal-nav-container">
    <el-menu
      class="personal-nav-menu"
      :default-openeds="['1', '2']"
      :router="true">
      <el-submenu
        v-for="(personalMenuItem, index) in personalMenuList"
        :key="index"
        :index="personalMenuItem.index">
        <template slot="title">
          <i class="material-icons">{{ personalMenuItem.icon }}</i>
          <span>{{ personalMenuItem.title }}</span>
        </template>
        <el-menu-item
          v-for="(personalMenuChildrenItem, index) in personalMenuItem.children"
          :key="index"
          :route="personalMenuChildrenItem.path"
          :index="personalMenuChildrenItem.index"
          :class="{active: activeIndex === personalMenuChildrenItem.index}">
          <span>{{ personalMenuChildrenItem.title }}</span>
        </el-menu-item>
      </el-submenu>
    </el-menu>
  </section>
</template>

<script>
export default {
  name: 'personalNav',
  data () {
    return {
      activeIndex: null,
      personalMenuList: [
        {
          index: '1',
          title: '我的兼职',
          icon: 'personal',
          children: [
            { index: '1-1', title: '我的申请', path: '/account/personal/partTimeApply/list' }
          ]
        },
        {
          index: '2',
          title: '账户设置',
          icon: 'view_list',
          children: [
            { index: '2-1', title: '修改简历', path: '/account/personal/resume' }
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
      this.personalMenuList.find(personalMenuItem => {
        if (personalMenuItem.children) {
          return personalMenuItem.children.find(personalMenuChildrenItem => {
            const routerPathArr = route.path.split('/');
            const personalMenuChildrenItemPathArr = personalMenuChildrenItem.path.split('/');
            if (routerPathArr[1] + routerPathArr[2] + routerPathArr[3] === personalMenuChildrenItemPathArr[1] + personalMenuChildrenItemPathArr[2] + personalMenuChildrenItemPathArr[3]) {
              this.activeIndex = personalMenuChildrenItem.index;
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
