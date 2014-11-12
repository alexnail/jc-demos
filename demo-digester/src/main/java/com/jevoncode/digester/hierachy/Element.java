package com.jevoncode.digester.hierachy;

import java.util.ArrayList;
import java.util.List;

public class Element {
	public int id;
	public String tag;
	public List<Element> children = new ArrayList<Element>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public List<Element> getChildren() {
		return children;
	}

	public void setChildren(List<Element> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "Element [id=" + id + ", tag=" + tag + ", children=" + children + "]";
	}

	public void addChild(Element e) {
		this.children.add(e);
	}
}
