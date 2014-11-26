package com.transround.dev.localizationdemo;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.json.JSONException;

public interface FiguresJsonParser<T> {
	public List<T> parseJsonStream(InputStream jsonStream) throws IOException,
			JSONException;
}
