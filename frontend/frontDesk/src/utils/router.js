export default {
  getNewPartTimeSearchConditionUrlParams (currentParams, newParams = {}) {
    // 构建新对象
    const newPartTimeSearchUrlParams = {};
    // 添加当前的 url 参数
    Object.keys(currentParams).forEach(key => {
      newPartTimeSearchUrlParams[key] = currentParams[key];
    });

    // 替换成新的 url 参数
    Object.keys(newParams).forEach(key => {
      newPartTimeSearchUrlParams[key] = newParams[key];
    });

    return newPartTimeSearchUrlParams;
  }
};
