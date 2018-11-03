<template>
  <section class="home-container">
    <section class="part-time-container">
      <part-time-top/>
      <part-time-search-condition/>
      <part-time-search-result-list/>
    </section>
    <footer-nav/>
  </section>
</template>

<script>
import { mapState, mapActions } from 'vuex';
import NProgress from 'nprogress';
import routerUtils from '@/utils/router';
import PartTimeTop from '@/components/part-time/PartTimeTop';
import PartTimeSearchCondition from '@/components/part-time/PartTimeSearchCondition';
import PartTimeSearchResultList from '@/components/part-time/PartTimeSearchResultList';
import FooterNav from '@/components/footer/FooterNav';

export default {
  name: 'Home',
  components: {
    PartTimeTop,
    PartTimeSearchCondition,
    PartTimeSearchResultList,
    FooterNav,
    flag: false
  },
  computed: {
    ...mapState('location', [
      'regionList',
      'currentCity'
    ])
  },
  async created() {
    NProgress.start();
    // 初始化搜索结果
    await this.asyncEditPartTimeSearchResultListByUrlParams();
  },
  watch: {
    // 监听 regionList 城市是否加载完毕
    regionList: {
      async handler() {
        // 初始化搜索结果
        await this.asyncEditPartTimeSearchResultListByUrlParams();
      }
    },
    // 监听 url 搜索兼职
    $route: {
      async handler() {
        if (this.$route.path === '/home') {
          // 根据 url 参数初始化搜索结果
          await this.asyncEditPartTimeSearchResultListByUrlParams();
        }
      }
    }
  },
  methods: {
    ...mapActions('location', [
      'editCurrentCityByCityId'
    ]),
    ...mapActions('partTime', [
      'editPartTimeSearchCondition',
      'asyncEditPartTimeSearchResultList'
    ]),
    async asyncEditPartTimeSearchResultListByUrlParams() {
      if (this.regionList.length <= 0) {
        return;
      }
      // 初始化所在城市
      this.editCurrentCityByCityId({
        cityId: this.$route.query.cityId !== undefined ? this.$route.query.cityId : '0'
      });
      // 初始化搜索条件
      const partTimeSearchConditionURLParams = this.$route.query;
      this.editPartTimeSearchCondition({
        partTimeSearchCondition: routerUtils.getNewPartTimeSearchConditionUrlParams({
          cityId: this.currentCity.id,
          areaId: '0',
          partTimeTypeId: '0',
          partTimeSpeciesId: '0',
          keyword: '',
          pageNumber: '1',
          pageSize: '10'
        }, partTimeSearchConditionURLParams)
      });

      // 开始搜索兼职
      NProgress.start();
      await this.asyncEditPartTimeSearchResultList();
      NProgress.done();
    }
  }
};
</script>

<style lang="scss">
  .home-container {
    width: 100% !important;
    .part-time-container {
      width: 79%;
      margin-left: auto;
      margin-right: auto;
    }
  }

  // 模态框定位问题
  .el-loading-mask {
    top: 60px;
  }
</style>
