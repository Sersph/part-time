// vue.config.js
module.exports = {
  baseUrl: process.env.NODE_ENV === 'production'
    ? '/admin/'
    : '/',
  productionSourceMap: false,
  devServer: {
    port: 8081
  }
};
