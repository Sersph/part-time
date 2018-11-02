<template>
  <section class="operation-container">
    <tinymce :height="900" id="1" v-model="content"/>
  </section>
</template>

<script>
import Tinymce from '@/components/tinymce';

export default {
  name: 'EnterpriseCertificationOperation',
  components: {
    Tinymce
  },
  data() {
    return {
      content: `123`
    };
  }
};
</script>

<style lang="scss">
  .editor-content {
    margin-top: 20px;
  }
</style>
