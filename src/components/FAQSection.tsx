import { Accordion, AccordionContent, AccordionItem, AccordionTrigger } from '@/components/ui/accordion';
import { Card } from '@/components/ui/card';
import { HelpCircle } from 'lucide-react';

export function FAQSection() {
  const faqs = [
    {
      question: "How does the scoping process work?",
      answer: "After you submit your project brief through our wizard, we match you with a qualified developer who conducts a paid scoping session ($199). This creates a detailed roadmap, timeline, and fixed-price quote for your project. You get a complete understanding of what's needed before committing to full development."
    },
    {
      question: "What if I'm not satisfied with the work?",
      answer: "Our milestone-based escrow system ensures you only pay for work you approve. If you're not satisfied, we'll work with the developer to address your concerns. If the issues can't be resolved, we provide a full refund. Your success is our priority."
    },
    {
      question: "How are developers ('Finishers') vetted?",
      answer: "All Finishers go through a rigorous 5-step vetting process: technical interviews, portfolio reviews, communication assessments, reference checks, and test projects. We only accept the top 5% of applicants who demonstrate both technical excellence and strong product sense."
    },
    {
      question: "What types of projects work best on handoff?",
      answer: "handoff is ideal for well-defined digital products: web applications, mobile apps, SaaS platforms, e-commerce sites, and API development. Projects work best when you have a clear vision, target audience, and success metrics defined."
    },
    {
      question: "How long do projects typically take?",
      answer: "Project timelines vary based on complexity, but most handoff projects complete in 4-8 weeks. During the scoping session, you'll receive a detailed timeline with specific milestones. Our milestone-based approach keeps projects on track and transparent."
    },
    {
      question: "What happens to my intellectual property?",
      answer: "You retain 100% ownership of your intellectual property. All Finishers sign comprehensive NDAs before accessing your project. Source code, designs, and all deliverables are transferred to you upon project completion."
    },
    {
      question: "Can I communicate directly with my developer?",
      answer: "Absolutely! Direct communication is encouraged. You'll have access to your Finisher through our platform messaging system, scheduled check-ins, and milestone reviews. We facilitate great communication while providing oversight and support."
    },
    {
      question: "What if my project scope changes during development?",
      answer: "Scope changes are handled through our change request process. Your Finisher will assess the impact on timeline and budget. Changes are documented and approved before implementation, ensuring transparency and avoiding surprises."
    },
    {
      question: "Do you provide ongoing support after launch?",
      answer: "Yes! Many of our Finishers offer ongoing maintenance and support services. During the scoping session, you can discuss post-launch needs. We also provide resources for finding long-term technical partners within our network."
    },
    {
      question: "How is handoff different from other freelance platforms?",
      answer: "Unlike generic freelance platforms, handoff focuses specifically on turning visions into viable products. We provide structured scoping, vetted developers, milestone-based payments, and end-to-end project management. You get a technical partner, not just a hired hand."
    }
  ];

  return (
    <section className="py-24 bg-gray-50">
      <div className="max-w-4xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="text-center mb-16">
          <div className="w-16 h-16 bg-blue-100 rounded-full flex items-center justify-center mx-auto mb-6">
            <HelpCircle className="h-8 w-8 text-blue-600" />
          </div>
          <h2 className="text-4xl md:text-5xl font-bold text-gray-900 mb-6">
            Frequently Asked{' '}
            <span className="text-transparent bg-clip-text bg-gradient-to-r from-blue-600 to-purple-600">
              Questions
            </span>
          </h2>
          <p className="text-xl text-gray-600">
            Everything you need to know about handoff and how it works.
          </p>
        </div>

        <Card className="border-0 shadow-lg">
          <Accordion type="single" collapsible className="w-full">
            {faqs.map((faq, index) => (
              <AccordionItem key={index} value={`item-${index}`} className="border-b border-gray-100 last:border-b-0">
                <AccordionTrigger className="px-6 py-4 text-left hover:bg-gray-50 transition-colors">
                  <span className="font-semibold text-gray-900">{faq.question}</span>
                </AccordionTrigger>
                <AccordionContent className="px-6 pb-4">
                  <p className="text-gray-600 leading-relaxed">{faq.answer}</p>
                </AccordionContent>
              </AccordionItem>
            ))}
          </Accordion>
        </Card>

        <div className="mt-12 text-center">
          <div className="bg-white rounded-2xl p-8 shadow-lg">
            <h3 className="text-xl font-bold text-gray-900 mb-4">
              Still have questions?
            </h3>
            <p className="text-gray-600 mb-6">
              Our team is here to help you understand how handoff can bring your project to life.
            </p>
            <div className="flex flex-col sm:flex-row gap-4 justify-center">
              <a 
                href="mailto:hello@handoff.com" 
                className="inline-flex items-center justify-center px-6 py-3 border border-gray-300 shadow-sm text-base font-medium rounded-md text-gray-700 bg-white hover:bg-gray-50 transition-colors"
              >
                Email Us
              </a>
              <a 
                href="#" 
                className="inline-flex items-center justify-center px-6 py-3 border border-transparent text-base font-medium rounded-md text-white bg-blue-600 hover:bg-blue-700 transition-colors"
              >
                Schedule a Call
              </a>
            </div>
          </div>
        </div>
      </div>
    </section>
  );
}