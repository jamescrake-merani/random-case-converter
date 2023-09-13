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

(defn entry-box [value on-change]
  [:textarea.textarea.textarea-bordered.flex-1
   {:on-change on-change
    :value value} ])

(defn base []
  (let [to-convert (r/atom "")]
    (fn []
      [:div.container.mx-auto.my-4
       [:h1.text-center.text-2xl.font-bold "Random Case Converter"]
       [:div.flex.space-x-2.py-4
        [entry-box @to-convert #(reset! to-convert (-> % .-target .-value))]
        [entry-box (convert-to-random-case @to-convert) #(.showModal (.getElementById js/document "oi-modal"))]]
       [:dialog {:id "oi-modal" :className "modal"}
        [:div.modal-box
         [:p.py-4 "Oi! You're not supposed to edit this box!"]]
        [:form {:method "dialog" :className "modal-backdrop"}
         [:button "close"]]]])))

(defn render
  []
  (rd/render [base]
             (.getElementById js/document "root")))
