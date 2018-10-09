<template>
  <section class="part-time-search-result-list-container">
    <div class="part-time-result-list">
      <router-link href="javascript:void(0)" class="part-time-item"
                   v-for="(partTimeItem, index) in partTimeSearchResultList"
                   :key="index"
                   :to="`/partTime/detail/${index}`"
      >
        <el-badge class="part-time-species">
          <el-button size="small" :title="partTimeItem.type">{{ partTimeItem.type }}</el-button>
        </el-badge>
        <span class="part-time-title" :title="partTimeItem.title">{{ partTimeItem.title }}</span>
        <span class="part-time-area">
          <i class="material-icons">location_on</i>
          <span :title="partTimeItem.location">{{ partTimeItem.location }}</span>
        </span>
        <span class="part-time-price-settle-type">
          <i class="material-icons">attach_money</i>
          <span :title="partTimeItem.viewCount">{{ partTimeItem.price }}元 / {{ partTimeItem.priceType }}</span>
        </span>
        <span class="part-time-settle-type">
          <span :title="partTimeItem.createdAt">{{ partTimeItem.priceType }}结</span>
        </span>
      </router-link>
    </div>
    <section class="part-time-search-result-p">
      <el-pagination
        background
        layout="prev, pager, next"
        @current-change="searchPartTime"
        :page-size="pageSize"
        :pager-count="pageCount"
        :current-page="currentPage"
        :total="total">
      </el-pagination>
    </section>
  </section>
</template>

<script>
import { mapState } from 'vuex';
import routerUtils from '@/utils/router';

export default {
  name: 'PartTimeList',
  data () {
    return {
      pageSize: 10,
      pageCount: 11,
      currentPage: 0,
      total: 1000
    };
  },
  computed: {
    ...mapState('partTime', [
      'partTimeSearchCondition',
      'partTimeSearchResultList'
    ])
  },
  watch: {
    partTimeSearchCondition: {
      deep: true,
      handler (partTimeSearchCondition) {
        // init 分页
        if (this.currentPage !== partTimeSearchCondition.limitStart / 10) {
          this.currentPage = partTimeSearchCondition.limitStart / 10;
        }
      }
    }
  },
  methods: {
    async searchPartTime (currentPage) {
      const limitStart = currentPage * this.pageSize;
      const limitSize = this.pageSize;
      // url 更新兼职搜索分页
      this.$router.push({
        path: this.$router.pah,
        query: routerUtils.getNewPartTimeSearchConditionUrlParams(this.$route.query, {
          limitStart,
          limitSize
        })
      });
    }
  }
};
</script>

<style lang="scss">
  .part-time-search-result-list-container {
    margin-top: 20px;
    .part-time-result-list {
      display: flex;
      flex-direction: column;
      border: 1px solid #dadada;
      border-radius: 3px;
      .part-time-item {
        display: flex;
        align-items: center;
        padding: 15px 20px;
        border-bottom: 1px solid #dadada;
        color: #606266;
        transition: background-color .15s, border-color .15s;
        .el-badge {
          button {
            width: 80px;
            margin-right: 20px;
            padding: 9px 15px;
            border-radius: 3px;
            border: 1px solid #dcdfe6;
            text-align: left;
            font-size: 12px;
            transition: none;
            span {
              display: block;
              overflow: hidden;
              white-space: nowrap;
              text-overflow: ellipsis;
            }
          }
          button:active,
          button:focus,
          button:hover {
            color: #666;
            background-color: #fff;
          }
        }
        span {
          .material-icons {
            width: 20px;
            height: 20px;
            margin-right: 10px;
            font-size: 21px;
          }
        }
        .part-time-species {
          width: 80px;
          margin-right: 29px;
        }
        .part-time-title,
        .part-time-area,
        .part-time-price-settle-type,
        .part-time-settle-type {
          overflow: hidden;
          font-size: 15px;
          white-space: nowrap;
          text-overflow: ellipsis;
          .material-icons {
            width: 20px;
            height: 20px;
            position: relative;
            top: .3rem;
          }
        }
        .part-time-title {
          width: 50%;
          font-weight: bold;
          font-size: 16px;
        }
        .part-time-area {
          width: 15%;
        }
        .part-time-price-settle-type {
          width: 15%;
        }
        .part-time-settle-type {
          width: 15%;
        }
      }
      .part-time-item:last-child {
        border-bottom: 0;
      }
      .part-time-item:hover {
        background-color: #ecf5ff;
      }
    }
    .part-time-search-result-p {
      display: flex;
      justify-content: center;
      padding-top: 30px;
      padding-bottom: 100px;
      .el-pagination.is-background {
        button,
        li {
          width: 40px;
          height: 40px;
          line-height: 40px;
          font-size: 16px;
        }
      }
    }
  }
</style>
