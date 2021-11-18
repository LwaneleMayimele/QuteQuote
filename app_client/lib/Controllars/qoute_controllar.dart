import 'package:app_client/DTO/quote.dart';
import 'package:app_client/Model/quote_model.dart';
import 'package:app_client/View/add_quote.dart';
import 'package:app_client/View/view_quotes.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class QuoteControllar extends ChangeNotifier {
  QuoteModel model = QuoteModel();

  void addQuote(String name, String quote) {
    model.postQuote(name, quote);
    notifyListeners();
  }

  Future<List<QuoteDTO>> view() async {
    return await model.getAllQuote();
  }

  Future<List<QuoteDTO>> get quoteList async {
    return await model.getAllQuote();
  }

  void clear() {}

  void saveQuote() {}

  void btnViewAllQuotes(BuildContext context, List<QuoteDTO> quotes) {
    Navigator.push(
        context, MaterialPageRoute(builder: (context) => ViewQuotes(quotes)));
    notifyListeners();
  }

  void btnViewQuote(BuildContext context) {
    Navigator.push(
        context, MaterialPageRoute(builder: (context) => const AddQuotes()));
    notifyListeners();
  }
}
