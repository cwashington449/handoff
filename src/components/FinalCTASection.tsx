import { Button } from '@/components/ui/button';
import { ArrowRight, Sparkles } from 'lucide-react';
import { Link } from 'react-router-dom';
import { ProjectScopingWizard } from './ProjectScopingWizard';
import { analytics } from '@/lib/analytics';

export function FinalCTASection() {
  return (
    <section className="py-24 bg-gradient-to-br from-blue-600 via-purple-600 to-pink-600 relative overflow-hidden">
      {/* Background Elements */}
      <div className="absolute inset-0 bg-black/10"></div>
      <div className="absolute top-10 left-10 w-32 h-32 bg-white/10 rounded-full blur-xl"></div>
      <div className="absolute bottom-10 right-10 w-40 h-40 bg-white/10 rounded-full blur-xl"></div>
      <div className="absolute top-1/2 left-1/2 transform -translate-x-1/2 -translate-y-1/2 w-60 h-60 bg-white/5 rounded-full blur-2xl"></div>
      
      <div className="relative max-w-4xl mx-auto px-4 sm:px-6 lg:px-8 text-center">
        <div className="mb-8">
          <Sparkles className="h-16 w-16 text-white mx-auto mb-6 animate-pulse" />
        </div>
        
        <h2 className="text-4xl md:text-6xl font-bold text-white mb-6 leading-tight">
          Ready to move your project forward?
        </h2>
        
        <p className="text-xl md:text-2xl text-white/90 mb-12 leading-relaxed max-w-2xl mx-auto">
          Join thousands of creators and developers who are building the future, one handoff at a time.
        </p>

        <div className="flex flex-col sm:flex-row gap-6 justify-center items-center">
          <div className="[&>button]:bg-white [&>button]:text-gray-900 [&>button]:hover:bg-gray-100 [&>button]:font-semibold [&>button]:shadow-lg [&>button]:border-0">
            <ProjectScopingWizard />
          </div>
          
          <Link to="/finishers">
            <Button 
              variant="outline" 
              size="lg"
              className="border-2 border-white bg-transparent text-white hover:bg-white hover:text-gray-900 px-8 py-4 text-lg font-semibold rounded-xl shadow-lg hover:shadow-xl transition-all duration-300 transform hover:scale-105"
              onClick={() => analytics.buttonClick('become_finisher_cta', 'final_cta_section')}
            >
              Become a Finisher
              <ArrowRight className="ml-2 h-5 w-5" />
            </Button>
          </Link>
        </div>

        <div className="mt-12 text-white/80">
          <p className="text-sm">
            ✨ No setup fees • 🔒 Secure payments • 🚀 Start in minutes
          </p>
        </div>
      </div>
    </section>
  );
}
