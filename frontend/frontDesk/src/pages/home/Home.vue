<template>
  <section class="home-container">
    <part-time-top/>
    <part-time-search-condition/>
    <part-time-search-result-list/>
  </section>
</template>

<script>
import PartTimeTop from '@/components/part-time/PartTimeTop';
import PartTimeSearchCondition from '@/components/part-time/PartTimeSearchCondition';
import PartTimeSearchResultList from '@/components/part-time/PartTimeSearchResultList';

export default {
  name: 'Home',
  components: {
    PartTimeTop,
    PartTimeSearchCondition,
    PartTimeSearchResultList
  }
};
</script>

<style lang="scss">

</style>
