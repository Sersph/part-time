<template>
  <section class="part-time-search-condition-container">
    <section class="part-time-search-condition-item">
      <div class="condition-name">区域</div>
      <div class="condition-list">
        <el-radio-group v-model="areaId">
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
        <el-radio-group v-model="typeId">
          <div class="item" @click="setTypeId(0)">
            <el-radio-button :label="0">不限</el-radio-button>
          </div>
          <div class="item"
               v-for="(typeItem, index) in typeList"
               :key="index"
               @click="setTypeId(typeItem.id)"
          >
            <el-radio-button :label="typeItem.id">{{ typeItem.name }}</el-radio-button>
            <sup v-if="typeItem.isHot">hot</sup>
          </div>
        </el-radio-group>
      </div>
    </section>
    <section class="part-time-search-condition-item">
      <div class="condition-name">种类</div>
      <div class="condition-list">
        <el-radio-group v-model="speciesId">
          <div class="item" @click="setSpeciesId(0)">
            <el-radio-button :label="0">不限</el-radio-button>
          </div>
          <div class="item"
               v-for="(speciesItem, index) in speciesList"
               :key="index"
               @click="setSpeciesId(speciesItem.id)"
          >
            <el-radio-button :label="speciesItem.id">{{ speciesItem.name }}</el-radio-button>
            <sup v-if="speciesItem.isHot">hot</sup>
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
  data () {
    return {
      typeList: [
        { id: 2, name: '短期兼职' },
        { id: 3, name: '长期兼职' },
        { id: 4, name: '寒假工' },
        { id: 5, name: '暑假工' }
      ],
      speciesList: [
        { id: 2, name: '其他' },
        { id: 3, name: '派单' },
        { id: 4, name: '销售' },
        { id: 5, name: '翻译' },
        { id: 6, name: '客服' },
        { id: 7, name: '演出' },
        { id: 8, name: '文员' },
        { id: 9, name: '临时工' },
        { id: 10, name: '实习' },
        { id: 11, name: '临时工' },
        { id: 12, name: '临时工', isHot: true },
        { id: 13, name: '临时工', isHot: true },
        { id: 14, name: '临时工' },
        { id: 15, name: '临时工', isHot: true }
      ],
      areaId: 0,
      typeId: 0,
      speciesId: 0
    };
  },
  computed: {
    ...mapState('partTime', [
      'partTimeSearchCondition'
    ]),
    ...mapState('location', [
      'currentCity'
    ])
  },
  watch: {
    partTimeSearchCondition: {
      deep: true,
      handler (partTimeSearchCondition) {
        // init 搜索地区
        if (this.areaId !== partTimeSearchCondition.areId) {
          this.areaId = partTimeSearchCondition.areaId;
        }
        // init 搜索类型
        if (this.typeId !== partTimeSearchCondition.typeId) {
          this.typeId = partTimeSearchCondition.typeId;
        }
        // init 搜索种类
        if (this.speciesId !== partTimeSearchCondition.speciesId) {
          this.speciesId = partTimeSearchCondition.speciesId;
        }
      }
    }
  },
  methods: {
    async setAreaId (areaId) {
      if (this.areaId === areaId) return;
      // url 更新兼职搜索地区
      this.$router.push({
        path: this.$router.pah,
        query: routerUtils.getNewPartTimeSearchConditionUrlParams(this.$route.query, {
          areaId
        })
      });
    },
    async setTypeId (typeId) {
      if (this.typeId === typeId) return;
      // url 更新兼职搜索类型
      this.$router.push({
        path: this.$router.pah,
        query: routerUtils.getNewPartTimeSearchConditionUrlParams(this.$route.query, {
          typeId
        })
      });
    },
    async setSpeciesId (speciesId) {
      if (this.speciesId === speciesId) return;
      // url 更新兼职搜索类型
      this.$router.push({
        path: this.$router.pah,
        query: routerUtils.getNewPartTimeSearchConditionUrlParams(this.$route.query, {
          speciesId
        })
      });
    }
  }
};
</script>

<style lang="scss">
  .part-time-search-condition-container {
    padding: 0 20px 5px;
    border: 1px solid #dadada;
    border-radius: 3px;
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
