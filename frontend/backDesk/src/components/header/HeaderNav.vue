<template>
  <section class="header-container">
    <transition name="slide-left" mode="out-in">
      <el-breadcrumb separator="/" v-show="opacityFlag">
        <el-breadcrumb-item
          v-for="(router, index) in currentRoute.matched"
          :key="index"
        >{{ router.meta.name }}
        </el-breadcrumb-item>
      </el-breadcrumb>
    </transition>
    <section class="user-action-container">
      <el-dropdown trigger="click">
      <span class="el-dropdown-link">
        <i class="material-icons">personal</i>
        <i class="el-icon-arrow-down el-icon--right"></i>
      </span>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item>退出登陆</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </section>
  </section>
</template>

<script>
export default {
  name: 'HeaderNav',
  data () {
    return {
      opacityFlag: true,
      currentRoute: {}
    };
  },
  async created () {
    // 刷新面包屑导航
    this.editCurrentRoute(this.$route);
    // 监听路由刷新面包屑导航
    this.$router.beforeEach((to, from, next) => {
      this.editCurrentRoute(to);
      next();
    });
  },
  methods: {
    editCurrentRoute (route) {
      this.opacityFlag = false;
      setTimeout(() => {
        this.currentRoute = route;
        this.opacityFlag = true;
      }, 300);
    }
  }
};
</script>

<style lang="scss">
  .header-container {
    display: flex;
    justify-content: space-between;
    align-items: center;
    .el-breadcrumb {
      padding: 23px 0;
    }
    .user-action-container {
      display: flex;
      align-items: center;
      .el-dropdown {
        .el-dropdown-link {
          display: flex;
          align-items: center;
          width: 100%;
          height: 100%;
          cursor: pointer;
          .material-icons {
            width: 40px;
            height: 40px;
            font-size: 40px;
          }
          .el-icon-arrow-down {
            margin-left: 0;
          }
        }
      }
    }
  }
</style>
