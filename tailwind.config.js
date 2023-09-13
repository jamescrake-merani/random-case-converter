/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["src/random_case_converter/main.cljs"],
  theme: {
    extend: {},
  },
  plugins: [require("daisyui")],
}

