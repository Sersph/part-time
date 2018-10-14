<template>
  <section class="breadcrumb-container">
    <transition name="slide-left" mode="out-in">
      <el-breadcrumb separator="/" v-show="opacityFlag">
        <el-breadcrumb-item
          v-for="(currentRouteMatchedItem, index) in currentRouteMatched"
          :key="index"
        >{{ currentRouteMatchedItem.meta.name }}
        </el-breadcrumb-item>
      </el-breadcrumb>
    </transition>
  </section>
</template>

<script>
export default {
  name: 'Breadcrumb',
  data () {
    return {
      opacityFlag: true,
      currentRouteMatched: {}
    };
  },
  async created () {
    // 刷新面包屑导航
    this.editCurrentRouteMatched(this.$route);
    // 监听路由刷新面包屑导航
    this.$router.beforeEach((to, from, next) => {
      this.editCurrentRouteMatched(to);
      next();
    });
  },
  methods: {
    editCurrentRouteMatched (route) {
      this.opacityFlag = false;
      setTimeout(() => {
        this.currentRouteMatched = route.matched.slice(2);
        this.opacityFlag = true;
      }, 300);
    }
  }
};
</script>

<style lang="scss">

</style>
