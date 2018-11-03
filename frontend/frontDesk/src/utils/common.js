export default {
  /**
   * 解析 url 参数为对象
   *  参数
   *    http://127.0.0.1?a=1&b=2
   *  返回
   *    { a: 1, b: 2 }
   *
   * @param url
   * @returns {string}
   */
  decodeUrl(url) {
    var result = {};
    var query = url.split('?')[1];
    var queryArr = query.split('&');
    queryArr.forEach(function (item) {
      var value = item.split('=')[1];
      var key = item.split('=')[0];
      result[key] = value;
    });
    return result;
  },

  /**
   * 解析对象为 url 参数
   *  参数
   *    { a: 1, b: 2 }
   *  返回
   *    ?a=1&b=2
   *
   * @param data
   */
  encodeUrl(data) {
    let dataStr = '';
    Object.keys(data).forEach(key => {
      dataStr += key + '=' + data[key] + '&';
    });
    dataStr = dataStr.substring(0, dataStr.lastIndexOf('&'));
    return dataStr;
  }
};
