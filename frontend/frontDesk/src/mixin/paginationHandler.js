export default {
  data() {
    return {
      pageNumber: 1,
      pageSize: 10,
      pages: 0,
      total: 0
    };
  },
  methods: {
    handlePageSize(pageSize) {
      this.pageSize = pageSize;
      this.searchData();
    },
    handlePageNumber(pageNumber) {
      this.pageNumber = pageNumber;
      this.searchData();
    }
  }
};
