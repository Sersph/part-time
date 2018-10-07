<template>
  <section class="select-city-container">
    <el-dialog title="切换城市" :visible.sync="selectCityContainerVisibleFlag" type="text">
      <el-form :model="selectCityForm" :rules="selectCityFormRules" ref="selectCityForm">
        <el-form-item prop="regionIds">
          <el-cascader
            :options="cityList"
            :props="{value: 'id', label: 'name'}"
            v-model="selectCityForm.regionIds">
          </el-cascader>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="selectCityContainerVisibleFlag = false">取 消</el-button>
        <el-button type="primary" @click="confirm">确 定</el-button>
      </div>
    </el-dialog>
  </section>
</template>

<script>
import { mapState } from 'vuex';

export default {
  name: 'SelectCity',
  data () {
    return {
      selectCityContainerVisibleFlag: false,
      selectCityForm: {},
      selectCityFormRules: {
        regionIds: [
          { required: true, message: '请选择城市', trigger: 'change' }
        ]
      }
    };
  },
  computed: {
    ...mapState('location', [
      'regionList'
    ]),
    cityList () {
      // 深度拷贝地区数据
      const newRegionList = JSON.parse(JSON.stringify(this.regionList));
      // 返回不带区域的地区数据
      return newRegionList.map(provinceItem => {
        if (provinceItem.children !== undefined) {
          provinceItem.children = provinceItem.children.map(cityItem => {
            delete cityItem.children;
            return cityItem;
          });
        }
        return provinceItem;
      });
    }
  },
  methods: {
    confirm () {
      this.$refs['selectCityForm'].validate((valid) => {
        if (valid) {
          // url 更新兼职搜索区域
          this.$router.push({
            path: this.$router.pah,
            query: {
              cityId: this.selectCityForm.regionIds[1]
            }
          });
          // 关闭区域选择框
          this.selectCityContainerVisibleFlag = false;
          // 清空表单
          this.$refs['selectCityForm'].resetFields();
        }
      });
    }
  }
};
</script>

<style lang="scss">
  .select-city-container {
    .el-dialog {
      width: 30%;
      min-width: 500px;
    }
  }
</style>
