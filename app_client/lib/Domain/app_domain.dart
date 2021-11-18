import 'dart:convert';

import 'package:app_client/DTO/quote.dart';
import 'package:app_client/Service/http_services.dart';
import 'package:flutter/widgets.dart';

class AppDomain extends ChangeNotifier {
  final List<QuoteDTO> _quotes = [];
  final HttpServices _services = HttpServices();

  Future<List<QuoteDTO>> getAllQuote() async {
    final quotesJson = await _services.getAllQuotes();
    List<QuoteDTO> quotes = [];
    var arrayOfJson = jsonDecode(quotesJson.body) as List;
    for (var element in arrayOfJson) {
      quotes.add(QuoteDTO(
          authorName: element['authorName'],
          quoteID: element['id'],
          quote: element['quote']));
    }
    return quotes;
  }

  bool isQuoteNameValid(String name, String author) {
    if (author.isNotEmpty && name.isNotEmpty) {
      return true;
    }
    return false;
  }

  Future<bool> postQuote(String name, String quote) async {
    if (isQuoteNameValid(name, quote)) {
      final result = await _services.postQuote(name, quote);
      if (result.statusCode == 201) {
        return true;
      }
      return false;
    }
    return false;
  }
}
