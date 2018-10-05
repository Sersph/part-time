<template>
  <section class="home-container">
    <part-time-top/>
    <part-time-search-condition/>
    <part-time-search-result-list/>
  </section>
</template>

<script>
import { mapState, mapActions } from 'vuex';
import NProgress from 'nprogress';
import routerUtils from '@/utils/router';
import PartTimeTop from '@/components/part-time/PartTimeTop';
import PartTimeSearchCondition from '@/components/part-time/PartTimeSearchCondition';
import PartTimeSearchResultList from '@/components/part-time/PartTimeSearchResultList';

export default {
  name: 'Home',
  components: {
    PartTimeTop,
    PartTimeSearchCondition,
    PartTimeSearchResultList
  },
  computed: {
    ...mapState('location', [
      'currentCity'
    ]),
    ...mapState('partTime', [
      'asyncEditPartTimeSearchResultListFlag'
    ])
  },
  async mounted () {
    this.asyncEditPartTimeSearchResultListByUrlParams();
  },
  watch: {
    // 监听 url 搜索兼职
    $route: {
      handler () {
        this.asyncEditPartTimeSearchResultListByUrlParams();
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
    async asyncEditPartTimeSearchResultListByUrlParams () {
      // 初始化所在城市
      this.editCurrentCityByCityId(this.$route.query.cityId !== undefined
        ? { cityId: this.$route.query.cityId }
        : { cityId: '0' }
      );

      // 初始化搜索条件
      const partTimeSearchConditionURLParams = this.$route.query;
      this.editPartTimeSearchCondition({
        partTimeSearchCondition: routerUtils.getNewPartTimeSearchConditionUrlParams({
          cityId: '0',
          areaId: '0',
          typeId: '0',
          speciesId: '0',
          keyword: '',
          limitStart: '0',
          endStart: '0'
        }, partTimeSearchConditionURLParams)
      });

      // 开始搜索兼职
      if (this.asyncEditPartTimeSearchResultListFlag === true) {
        NProgress.start();
        await this.asyncEditPartTimeSearchResultList();
        NProgress.done();
      }
    }
  }
};
</script>

<style lang="scss">
  // 模态框定位问题
  .el-loading-mask {
    top: 60px;
  }

  // nprogress
  #nprogress .bar {
    height: 5px;
    background: #0425c0;
    box-shadow: 0 0 10px rgba(17, 255, 242, 0.7);
  }

</style>
