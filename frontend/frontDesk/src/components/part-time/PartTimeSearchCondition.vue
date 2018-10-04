<template>
  <section class="part-time-search-condition-container">
    <section class="part-time-search-condition-item">
      <div class="condition-name">区域</div>
      <div class="condition-list">
        <el-radio-group v-model="selectAreaList">
          <div class="item"
               v-for="(area, index) in areaList"
               :key="index"
          >
            <el-radio-button
              :label="area.id">{{ area.name }}
            </el-radio-button>
            <sup v-if="area.isHot">hot</sup>
          </div>
        </el-radio-group>
      </div>
    </section>
    <section class="part-time-search-condition-item">
      <div class="condition-name">类型</div>
      <div class="condition-list">
        <el-radio-group v-model="selectTypeList">
          <div class="item"
               v-for="(typeItem, index) in typeList"
               :key="index"
          >
            <el-radio-button
              :label="typeItem.id">{{ typeItem.name }}
            </el-radio-button>
            <sup v-if="typeItem.isHot">hot</sup>
          </div>
        </el-radio-group>
      </div>
    </section>
    <section class="part-time-search-condition-item">
      <div class="condition-name">种类</div>
      <div class="condition-list">
        <el-radio-group v-model="selectSpeciesList">
          <div class="item"
               v-for="(species, index) in speciesList"
               :key="index"
          >
            <el-radio-button
              :label="species.id">{{ species.name }}
            </el-radio-button>
            <sup v-if="species.isHot">hot</sup>
          </div>
        </el-radio-group>
      </div>
    </section>
  </section>
</template>

<script>
export default {
  name: 'PartTimeSearchCondition',
  data () {
    return {
      areaList: [
        { id: 0, name: '不限' },
        { id: 1, name: '罗湖区' },
        { id: 2, name: '罗湖区' },
        { id: 3, name: '罗湖区' }
      ],
      typeList: [
        { id: 0, name: '不限' },
        { id: 1, name: '日结兼职' },
        { id: 2, name: '短期兼职' },
        { id: 3, name: '长期兼职' },
        { id: 4, name: '寒假工' },
        { id: 5, name: '暑假工' },
        { id: 6, name: '手机兼职', isHot: true }
      ],
      speciesList: [
        { id: 0, name: '不限' },
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
      selectAreaList: 0,
      selectTypeList: 0,
      selectSpeciesList: 0
    };
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
      margin-top: 20px;
      border-bottom: 1px solid #dadada;
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
          margin-bottom: 15px;
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
