package com.github.cmput301w13t04.food.model.query;

import java.util.ArrayList;
import java.util.List;

import com.github.cmput301w13t04.food.model.Recipe;

public class ResultsRecipe {
	private Hits hits;

	/**
	 * Get recipe at position of result
	 * 
	 * @param position
	 *            Position in results to return
	 * @return Requested recipe
	 */
	public Recipe getRecipe(int position) {
		return this.hits.getHits().get(position)._source;
	}

	public ArrayList<Recipe> getRecipes() {
		ArrayList<Recipe> recipes = new ArrayList<Recipe>();
		for (int i = 0; i < this.hits.getHits().size(); i++) {
			recipes.add(this.hits.getHits().get(i)._source);
		}
		return recipes;
	}

	public Hits getHits() {
		return this.hits;
	}

	public void setHits(Hits hits) {
		this.hits = hits;
	}

	public class Hits {
		private List<Hits2> hits;

		private Number max_score;
		private Number total;

		public List<Hits2> getHits() {
			return this.hits;
		}

		public void setHits(List<Hits2> hits) {
			this.hits = hits;
		}

		public Number getMax_score() {
			return this.max_score;
		}

		public void setMax_score(Number max_score) {
			this.max_score = max_score;
		}

		public Number getTotal() {
			return this.total;
		}

		public void setTotal(Number total) {
			this.total = total;
		}

		public class Hits2 {
			private String _id;
			private String _index;
			private Number _score;
			private Recipe _source;
			private String _type;

			public String get_id() {
				return this._id;
			}

			public void set_id(String _id) {
				this._id = _id;
			}

			public String get_index() {
				return this._index;
			}

			public void set_index(String _index) {
				this._index = _index;
			}

			public Number get_score() {
				return this._score;
			}

			public void set_score(Number _score) {
				this._score = _score;
			}

			public Recipe get_source() {
				return this._source;
			}

			public void set_source(Recipe ingredient) {
				this._source = ingredient;
			}

			public String get_type() {
				return this._type;
			}

			public void set_type(String _type) {
				this._type = _type;
			}
		}
	}
}