import 'package:app_client/DTO/quote.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:http/http.dart' as http;

class ViewQuotes extends StatelessWidget {
  List<QuoteDTO> quotes;

  ViewQuotes(this.quotes, {Key? key}) : super(key: key);
  var client = http.Client();
  @override
  Widget build(BuildContext context) {
    return SafeArea(
        child: Scaffold(
            appBar: AppBar(
              backgroundColor: Colors.teal,
              title: const Text(
                "Quotes",
                style: TextStyle(
                  fontSize: 30.0,
                  fontWeight: FontWeight.bold,
                ),
              ),
              centerTitle: true,
            ),
            body: Center(
                child: ListView.builder(
                    itemCount: quotes.length,
                    itemBuilder: (context, index) {
                      return Center(
                        child: Card(
                          color: Colors.teal[100],
                          child: Column(
                            mainAxisSize: MainAxisSize.min,
                            children: <Widget>[
                              ListTile(
                                leading: const Icon(Icons.auto_stories),
                                title: Text(
                                  quotes[index].authorName,
                                  style: const TextStyle(
                                    fontWeight: FontWeight.bold,
                                  ),
                                ),
                                subtitle: Text(quotes[index].quote),
                              ),
                              Row(
                                mainAxisAlignment: MainAxisAlignment.end,
                                children: <Widget>[
                                  TextButton(
                                    child: const Text('DELETE'),
                                    onPressed: () {/* ... */},
                                  ),
                                  const SizedBox(width: 8),
                                  TextButton(
                                    child: const Text('FULL VIEW'),
                                    onPressed: () {/* ... */},
                                  ),
                                  const SizedBox(width: 8),
                                ],
                              ),
                            ],
                          ),
                        ),
                      );
                    }))));
  }
}
