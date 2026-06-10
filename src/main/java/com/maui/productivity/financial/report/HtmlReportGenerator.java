package com.maui.productivity.financial.report;

import com.maui.productivity.financial.model.DataAccessor;
import com.maui.productivity.financial.model.TaggedTransaction;
import com.maui.productivity.financial.model.Transaction;
import j2html.tags.DomContent;
import j2html.tags.DomContentJoiner;
import j2html.tags.specialized.TableTag;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.maui.productivity.financial.manager.ReportManager.CURRENCY_FORMAT;
import static j2html.TagCreator.b;
import static j2html.TagCreator.body;
import static j2html.TagCreator.each;
import static j2html.TagCreator.h1;
import static j2html.TagCreator.h2;
import static j2html.TagCreator.h3;
import static j2html.TagCreator.html;
import static j2html.TagCreator.style;
import static j2html.TagCreator.table;
import static j2html.TagCreator.tbody;
import static j2html.TagCreator.td;
import static j2html.TagCreator.th;
import static j2html.TagCreator.thead;
import static j2html.TagCreator.title;
import static j2html.TagCreator.tr;

@Log4j2
public class HtmlReportGenerator {

    private static final String RIGHT_ALIGN = "right-align";

    public <P, A extends DataAccessor<P>> String reportCategoryTotalsByPeriod(final List<P> periods,
                                                                              final A dataAccessor,
                                                                              final Set<String> categories,
                                                                              final Map<String, Double> categoryBudget) {

        final TableTag table = table().with(
                thead(
                        tr(
                                th("Category"),
                                th("Budget"),
                                each(periods, period -> th(period.toString()))
                        )
                ),
                tbody(
                        each(categories, category -> tr(
                                td(category),
                                td(CURRENCY_FORMAT.format(categoryBudget.get(category))).withClass(RIGHT_ALIGN),
                                each(periods, period -> {
                                    final List<TaggedTransaction> transactions = dataAccessor.getTransactions(period, category);
                                    final double categoryTotal = transactions.stream()
                                            .map(TaggedTransaction::getTransaction)
                                            .mapToDouble(Transaction::getAmount)
                                            .sum();
                                    return td(CURRENCY_FORMAT.format(categoryTotal)).withClass(RIGHT_ALIGN);
                                })
                            )
                        )
                )
        );

        final String title = String.format("Totals from %s to %s", periods.getFirst(), periods.getLast());

        return render(title, List.of(table));
    }

    public <P, A extends DataAccessor<P>> String reportRollup(final List<P> periods, final A dataAccessor, final Set<String> categories) {

        final Map<String, TableTag> periodTables = new HashMap<>();
        double total = 0.0;
        for (final P period : periods) {
            final List<List<String>> dataRows = new ArrayList<>();
            log.info(period);
            double periodTotal = 0.0;
            for (final String category : categories) {
                log.info("*** {}", category);
                dataRows.add(List.of(category));
                final List<TaggedTransaction> transactions = dataAccessor.getTransactions(period, category);
                double categoryTotal = 0.0;
                for (final TaggedTransaction taggedTransaction : transactions) {
                    final Transaction transaction = taggedTransaction.getTransaction();
                    final String currencyAmount = CURRENCY_FORMAT.format(transaction.getAmount());
                    categoryTotal += transaction.getAmount();
                    log.info("    {}: {} - {}", transaction.getDate(), currencyAmount, transaction.getDescription());
                    dataRows.add(List.of(transaction.getDate().toString(), currencyAmount, transaction.getDescription()));
                }
                periodTotal += categoryTotal;
                final String currencyCategoryTotal = CURRENCY_FORMAT.format(categoryTotal);
                log.info("         Total: {}",currencyCategoryTotal);
                dataRows.add(List.of("Total", currencyCategoryTotal));
            }
            total += periodTotal;
            final String currencyPeriodTotal = CURRENCY_FORMAT.format(periodTotal);
            log.info("Total({}): {}", period, currencyPeriodTotal);
            dataRows.add(List.of(String.format("Total(%s)", period), currencyPeriodTotal));

            final TableTag periodTable = table().with(
                    thead(
                            tr(
                                    th("Date"),
                                    th("Amount"),
                                    th( "Description")
                            )
                    ),
                    tbody(
                            each(dataRows, row -> {
                                if (row.size() == 1) {
                                    return tr(td(b(row.get(0))).attr("colspan", "3"));
                                }
                                if (row.size() == 2) {
                                    return tr(td(row.get(0)), td(row.get(1)).withClass(RIGHT_ALIGN));
                                }

                                return tr(td(row.get(0)), td(row.get(1)).withClass(RIGHT_ALIGN), td(row.get(2)));
                            })
                    )
            );
            periodTables.put(period.toString(), periodTable);
        }

        final String currencyTotal = CURRENCY_FORMAT.format(total);
        log.info("         Total: {}", currencyTotal);

        final String title = (periods.size() == 1)
                ? "Rollup " + periods.getFirst().toString()
                : String.format("Rollup %s to %s", periods.getFirst(), periods.getLast());
        final List<DomContent> bodyContent = new ArrayList<>();
        periods.forEach(period -> {
            final String periodKey = period.toString();
            bodyContent.add(h2(periodKey));
            bodyContent.add(periodTables.get(periodKey));
        });
        bodyContent.add(h3("Overall Total = " + currencyTotal));

        return render(title, bodyContent);
    }

    public <P, A extends DataAccessor<P>> String reportBudgetProgress(final String title, final List<List<String>> dataRows) {
        final TableTag table = table().with(
                thead(
                        tr(
                                th("Category"),
                                th("Acutal"),
                                th( "Budget"),
                                th( "Budget %"),
                                th( "YTD Budget"),
                                th( "YTD Budget %")
                        )
                ),
                tbody(
                        each(dataRows, row -> {
                            if (row.size() <= 1) {
                                final String text = row.size() == 0 ? "" : row.get(0);
                                return tr(td(b(text)).attr("colspan", "5"));
                            }

                            return tr(
                                    each( row, item -> td(item).withClass(RIGHT_ALIGN)));
                        })
                )
        );

        return render(title, List.of(table));
    }

    private String render(final String title, final List<DomContent> bodyContent) {
        return html(
                style(
                        "table, th, td {" +
                                "border: 1px solid black;" +
                                "}" +
                                ".right-align {" +
                                "            text-align: right;\n" +
                                "        }"),
                title(title),
                body(h1(title), each(bodyContent.stream()))
        ).renderFormatted();
    }

}
