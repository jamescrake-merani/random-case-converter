;;    Random Case Convertor
;;    Copyright (C) 2023 James Crake-Merani
;;
;;    This program is free software: you can redistribute it and/or modify
;;    it under the terms of the GNU General Public License as published by
;;    the Free Software Foundation, either version 3 of the License, or
;;    (at your option) any later version.
;;
;;    This program is distributed in the hope that it will be useful,
;;    but WITHOUT ANY WARRANTY; without even the implied warranty of
;;    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
;;    GNU General Public License for more details.
;;
;;    You should have received a copy of the GNU General Public License
;;    along with this program.  If not, see <https://www.gnu.org/licenses/>.

(ns random-case-convertor.main
  [:require [reagent.core :as r]
   [reagent.dom :as rd]
   [clojure.string :as s]])

(defn random-char-case [c]
  (if (= (Math/round (rand)) 0)
    (s/upper-case c)
    (s/lower-case c)))

(defn convert-to-random-case [to-convert]
  (apply str (map random-char-case to-convert)))

(defn base []
  (let [to-convert (r/atom "")]
    (fn []
      [:div.container.mx-auto.my-4
       [:h1.text-center.text-2xl.font-bold "Random Case Converter"]
       [:input.input.input-bordered {:on-change #(reset! to-convert (-> % .-target .-value)) :value @to-convert} ]
       [:input.input.input-bordered {:readonly true :value (convert-to-random-case @to-convert)}]
       [:div.bg-blue-200 ]])))

(defn render
  []
  (rd/render [base]
             (.getElementById js/document "root")))
