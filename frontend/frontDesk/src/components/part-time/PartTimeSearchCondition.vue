<template>
  <section class="part-time-search-condition-container">
    <section class="part-time-search-condition-item">
      <div class="condition-name">区域</div>
      <div class="condition-list">
        <el-radio-group v-model="partTimeSearchCondition.areaId">
          <div class="item" @click="setAreaId(0)">
            <el-radio-button :label="0">不限</el-radio-button>
          </div>
          <div class="item"
               v-for="(areaItem, index) in currentCity.children"
               :key="index"
               @click="setAreaId(areaItem.id)"
          >
            <el-radio-button :label="areaItem.id">{{ areaItem.name }}</el-radio-button>
            <sup v-if="areaItem.isHot">hot</sup>
          </div>
        </el-radio-group>
      </div>
    </section>
    <section class="part-time-search-condition-item">
      <div class="condition-name">类型</div>
      <div class="condition-list">
        <el-radio-group v-model="partTimeSearchCondition.partTimeTypeId">
          <div class="item" @click="setTypeId(0)">
            <el-radio-button :label="0">不限</el-radio-button>
          </div>
          <div class="item"
               v-for="(partTimeTypeItem, index) in partTimeBaseInfo.partTimeTypeList"
               :key="index"
               @click="setTypeId(partTimeTypeItem.id)"
          >
            <el-radio-button :label="partTimeTypeItem.id">{{ partTimeTypeItem.name }}</el-radio-button>
            <sup v-if="partTimeTypeItem.isHot">hot</sup>
          </div>
        </el-radio-group>
      </div>
    </section>
    <section class="part-time-search-condition-item">
      <div class="condition-name">种类</div>
      <div class="condition-list">
        <el-radio-group v-model="partTimeSearchCondition.partTimeSpeciesId">
          <div class="item" @click="setSpeciesId(0)">
            <el-radio-button :label="0">不限</el-radio-button>
          </div>
          <div class="item"
               v-for="(partTimeSpeciesItem, index) in partTimeBaseInfo.partTimeSpeciesList"
               :key="index"
               @click="setSpeciesId(partTimeSpeciesItem.id)"
          >
            <el-radio-button :label="partTimeSpeciesItem.id">{{ partTimeSpeciesItem.name }}</el-radio-button>
            <sup v-if="partTimeSpeciesItem.isHot">hot</sup>
          </div>
        </el-radio-group>
      </div>
    </section>
  </section>
</template>

<script>
import { mapState } from 'vuex';
import routerUtils from '@/utils/router';

export default {
  name: 'PartTimeSearchCondition',
  computed: {
    ...mapState('partTime', [
      'partTimeBaseInfo',
      'partTimeSearchCondition'
    ]),
    ...mapState('location', [
      'currentCity'
    ])
  },
  methods: {
    async setAreaId(areaId) {
      if (this.areaId === areaId) return;
      // url 更新兼职搜索地区
      this.$router.push({
        path: this.$router.pah,
        query: routerUtils.getNewPartTimeSearchConditionUrlParams(this.$route.query, {
          areaId
        })
      });
    },
    async setTypeId(partTimeTypeId) {
      if (this.partTimeTypeId === partTimeTypeId) return;
      // url 更新兼职搜索类型
      this.$router.push({
        path: this.$router.pah,
        query: routerUtils.getNewPartTimeSearchConditionUrlParams(this.$route.query, {
          partTimeTypeId
        })
      });
    },
    async setSpeciesId(partTimeSpeciesId) {
      if (this.partTimeSpeciesId === partTimeSpeciesId) return;
      // url 更新兼职搜索类型
      this.$router.push({
        path: this.$router.pah,
        query: routerUtils.getNewPartTimeSearchConditionUrlParams(this.$route.query, {
          partTimeSpeciesId
        })
      });
    }
  }
};
</script>

<style lang="scss">
  .part-time-search-condition-container {
    padding: 1px 20px;
    box-shadow: 0 0 50px rgba(0, 0, 0, 0.1);
    .part-time-search-condition-item {
      display: flex;
      min-height: 48px;
      margin-top: 20px;
      border-bottom: 1px solid #dadada;
      transition: height 1s;
      &:last-child {
        border-bottom: 0;
      }
      .condition-name {
        width: 50px;
        margin-right: 55px;
        margin-top: 5px;
        text-align: right;
        color: #666;
      }
      .condition-list {
        flex: 1;
        .item {
          position: relative;
          display: inline-block;
          margin-right: 30px;
          margin-bottom: 20px;
          span {
            overflow: hidden;
            width: 80px;
            padding: 9px 15px;
            border-radius: 3px;
            border: 1px solid #dcdfe6;
            text-align: left;
            font-size: 12px;
            text-overflow: ellipsis;
            transition: none;
          }
          span:hover {
            color: #409EFF;
            box-shadow: none;
          }
          span:active {
            color: #3a8ee6;
            border-color: #3a8ee6;
            outline: 0;
          }
          sup {
            position: absolute;
            top: 0;
            right: 10px;
            padding: 0 6px;
            border-radius: 10px;
            height: 18px;
            line-height: 18px;
            font-size: 11px;
            text-align: center;
            background-color: #f56c6c;
            color: #fff;
            transform: translateY(-50%) translateX(100%);
          }
          .el-checkbox-button.is-checked .el-checkbox-button__inner {
            color: #409EFF;
            border-color: #c6e2ff;
            background-color: #ecf5ff;
            box-shadow: none;
          }
          .el-radio-button__orig-radio:checked + .el-radio-button__inner {
            color: #409EFF;
            border-color: #c6e2ff;
            background-color: #ecf5ff;
            box-shadow: none;
          }
        }
      }
    }
  }
</style>
