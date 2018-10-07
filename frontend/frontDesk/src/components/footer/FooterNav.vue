<template>
  <footer class="footer-nav-container">
    <section>
      <p>Copyright 兼职君</p>
    </section>
  </footer>
</template>

<script>
export default {
  name: 'FooterNav'
};
</script>

<style lang="scss">
.footer-nav-container {
  display: flex;
  justify-content: center;
  background: linear-gradient(60deg, #06f, #7da2ea);
  section {
    width: 83.33333%;
    text-align: center;
    padding: 50px;
    color: #fff;
  }
}
</style>
