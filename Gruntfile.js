module.exports = function(grunt) {
  grunt.initConfig({
    uglify: {
      core: {
        files: [{
          expand: true,
          cwd: 'src/main/resources',
          src: 'public/modules/**/*.js',
          dest: "target/classes",
          ext: '.min.js'
        }]
      }
    }
  });

  grunt.loadNpmTasks('grunt-contrib-uglify');
  grunt.registerTask('default', ['uglify']);
};